package com.barrosedijanio.finanasconjuntas.auth.data

import android.content.Context
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signInWithGoogle(context: Context, onResult:(response: Result, user: FirebaseUser?) -> Unit)

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        username: String,
        onResult: (response: Result) -> Unit,
    ): Unit

    suspend fun signInWithEmailAndPassword(email: String, password: String) : AuthResult?

    fun signOut(): Unit
}