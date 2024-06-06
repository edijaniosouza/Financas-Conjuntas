package com.barrosedijanio.finanasconjuntas.auth.data


import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.model.User
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun signInWithGoogle(context: Context, onResult:(response: Result, user: FirebaseUser?) -> Unit){

        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        try {
            val result = credentialManager.getCredential(context, request)
            val credential = result.credential
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val googleIdToken = googleIdTokenCredential.idToken
            val firebaseCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
            firebaseAuth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        task.result.user?.let { onResult(Result.OK, it) }
                    }
                }
        }catch (e: Exception){
            Log.e("firebaseAuth", "signInWithGoogle: ${e.message}", )
            e.message?.let { Result.Error(it) }?.let { onResult(it, null) }
            e.printStackTrace()
        }
    }

    override fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        username: String,
        onResult: (response: Result) -> Unit,
    ) {
        onResult(Result.Loading)

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result.user
                user?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                )?.addOnCompleteListener { updateTask ->
                    if (updateTask.isSuccessful)
                        onResult(Result.OK)
                    else
                        updateTask.exception?.message?.let { onResult(Result.Error(it)) }!!
                }
            }
        }
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) : AuthResult?{
        val signInWithEmailAndPassword =
            firebaseAuth.signInWithEmailAndPassword(email, password)
        return try {
            signInWithEmailAndPassword
                .await()
        } catch (e: Exception){
            null
        }
    }

    fun insertUserIntoDatabase(user: FirebaseUser){

    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}