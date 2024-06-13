package com.barrosedijanio.finanasconjuntas.firebase.domain.di

import com.barrosedijanio.finanasconjuntas.firebase.data.balance.AccountBalanceRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.data.config.ConfigRepository
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val firestoreModule = module {
    single <TransactionRepositoryImpl> {
        TransactionRepositoryImpl(get(), get(), get())
    }

    single <FirebaseFirestore>{
        FirebaseFirestore.getInstance()
    }

    single <AccountBalanceRepositoryImpl>{
        AccountBalanceRepositoryImpl(get(), get())
    }

    single <ConfigRepository>{
        ConfigRepository(get(), get())
    }
}