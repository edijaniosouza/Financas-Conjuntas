package com.barrosedijanio.finanasconjuntas

import android.app.Application
import com.barrosedijanio.finanasconjuntas.auth.domain.di.firebaseAuthModule
import com.barrosedijanio.finanasconjuntas.auth.domain.di.loginModule
import com.barrosedijanio.finanasconjuntas.firebase.domain.di.firestoreModule
import com.barrosedijanio.finanasconjuntas.home.domain.di.homeModule
import com.barrosedijanio.finanasconjuntas.transactions.di.transactionsModule
import com.barrosedijanio.finanasconjuntas.statement.domain.di.statementModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                homeModule,
                firebaseAuthModule,
                firestoreModule,
                loginModule,
                statementModule,
                transactionsModule
            )
        }
    }
}