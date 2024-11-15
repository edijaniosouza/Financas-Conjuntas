package com.barrosedijanio.finanasconjuntas.firebase.data.accountLink

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.LinkRequestStatus
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.RequestModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

const val LINK = "link"
const val LINKSETUP = "linkSetup"

class AccountLinkRepository(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth
) {
    private val linkCollection = firestore.collection(LINK)
    private val requestCollection = firestore.collection("shareLinkRequest")
    private val userId = auth.currentUser?.uid
    private val userIdLinkDocument = userId?.let { id -> linkCollection.document(id) }


    private val linkedAccountData = suspend {
        try {
            userIdLinkDocument?.get()?.await()?.data
        } catch (e: Exception) {
            Log.e("Firebase - AccountLinkRepository", "accountIsLinked: ${e.message}")
            null
        }
    }

    val isThereRequest = suspend {
        try {
            val allReceived =
                firestore.collection("shareLinkRequest")
                    .whereEqualTo("receiverId", "amanda")

            val pendingList = allReceived.whereEqualTo("status", "PENDING").get().await()
            if (pendingList.isEmpty) {
                null
            } else {
                pendingList.documents.first().id
            }
        } catch (e: Exception) {
            Log.e("Firebase - Requests", "isThereRequest: ${e.message}")
            null
        }
    }

    suspend fun getLinkAccount() = flow {
        val data = linkedAccountData()
        val linkedUsers: List<String> = data?.get("users")?.let {
            it as List<String>
        } ?: emptyList()

        emit(linkedUsers)
    }

//    suspend fun getRequests() = flow{
//        val requestDocument = isThereRequest()
//        requestDocument.documents.forEach {request ->
//            emit(request)
//        }
//    }

    suspend fun sendRequest() = flow {
        try {
            if (linkedAccountData() == null) {
                requestCollection.add(
                    RequestModel(
                        senderId = userId!!,
                        receiverId = null,
                    )
                ).await()
                emit(Result.OK(userId))
            } else {
                Log.i(LINKSETUP, "sendRequest: já existe um link para essa conta")
                emit(Result.Error("Erro ao realizar request"))
            }
        } catch (e: Exception) {
            Log.e("Firebase - Send Request", "sendRequest: ${e.message}")
            emit(Result.Error("Erro inesperado ao realizar request"))
        }
    }

    suspend fun updateRequest(linkRequestStatus: LinkRequestStatus) {
        try {
            val requestId = isThereRequest()
            requestId?.let {
                val document = requestCollection.document(it)
                updateLinkRequestStatus(document, linkRequestStatus)

                when (linkRequestStatus) {
                    LinkRequestStatus.ACCEPTED -> {
                        val senderId = document.get().await().data?.get("senderId")
                        val receiverId = document.get().await().data?.get("receiverId")
                        addNewLink(senderId, receiverId)
                    }

                    else -> {}
                }
            }
        } catch (e: Exception) {
            Log.e(
                "Firebase - Update Request",
                "updateRequest: AccountLinkRepository.updateRequest - ${e.message}",
            )
        }
    }

    private fun updateLinkRequestStatus(
        document: DocumentReference,
        linkRequestStatus: LinkRequestStatus
    ) {
        document.update(
            mapOf(
                "status" to linkRequestStatus
            )
        )
    }

    private fun addNewLink(
        senderId: Any?,
        receiverId: Any?
    ) = linkCollection.add(
        mapOf(
            "users" to listOf(senderId, receiverId)
        )
    )

}