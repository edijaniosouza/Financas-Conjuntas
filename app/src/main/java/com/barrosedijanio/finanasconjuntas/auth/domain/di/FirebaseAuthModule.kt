package com.barrosedijanio.finanasconjuntas.auth.domain.di

import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val firebaseAuthModule = module{
    single<AuthRepositoryImpl> {
        AuthRepositoryImpl(get())
    }

    single <FirebaseAuth>{
        FirebaseAuth.getInstance()
    }
}