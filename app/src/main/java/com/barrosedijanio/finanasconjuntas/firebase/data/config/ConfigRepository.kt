package com.barrosedijanio.finanasconjuntas.firebase.data.config

import android.util.Log
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.DATA
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

const val USER_CONFIG = "userConfig"
class ConfigRepository(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth,
) {
    private val dataCollection = firestore.collection(DATA)
    private val userId = auth.currentUser?.uid
    private val userIdDocument = userId?.let { dataCollection.document(it) }

    suspend fun configNewUser(userId: String,remember: Boolean) {
        dataCollection
            .document(userId)
            .collection("config")
            .add(
                Config(remember = remember)
            ).await()
    }

    suspend fun getUserConfig(){
        val configs = userIdDocument?.collection("config")?.get()?.await()
        Log.i(USER_CONFIG, "getUserConfig: $configs")
    }

}

data class Config(
    val remember: Boolean = false,
    val darkTheme: Boolean = false,
    val sendNotifications: Boolean = false,
    val sharedOn: Boolean = false,
)