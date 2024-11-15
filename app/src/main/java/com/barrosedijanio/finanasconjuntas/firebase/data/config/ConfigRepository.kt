package com.barrosedijanio.finanasconjuntas.firebase.data.config

import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.DATA
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import android.net.Uri
import com.barrosedijanio.finanasconjuntas.auth.domain.model.User
import com.google.firebase.firestore.DocumentReference

const val USER_CONFIG = "userConfig"
class ConfigRepository(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth,
) {
    private val dataCollection = firestore.collection(DATA)
    private val userId = auth.currentUser?.uid
    private val userIdDocument = userId?.let { dataCollection.document(it) }

    suspend fun configNewUser(user: User, remember: Boolean) {
        val doc = dataCollection
            .document(user.uid!!)

        addDefaultConfigValues(doc, remember)
        addDefaultUserValues(doc, user)
    }

    private fun addDefaultUserValues(
        doc: DocumentReference,
        user: User
    ) {
        doc.collection("userInfo")
            .document("main")
            .set(
                UserInfo(
                    name = user.username!!,
                    photoUrl = user.profilePictureUrl
                )
            )
    }

    private suspend fun addDefaultConfigValues(
        doc: DocumentReference,
        remember: Boolean
    ) {
        doc.collection("config")
            .add(
                Config(remember = remember)
            ).await()
    }

    suspend fun getUserConfig(){
        val configs = userIdDocument?.collection("config")?.get()?.await()
    }

}

data class Config(
    val remember: Boolean = false,
    val darkTheme: Boolean = false,
    val sendNotifications: Boolean = false,
    val sharedOn: Boolean = false,
)

data class UserInfo(
    val name: String,
    val photoUrl: Uri? = null,
)