package com.barrosedijanio.finanasconjuntas

import android.app.Application
import com.barrosedijanio.finanasconjuntas.di.viewmodel.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(loginModule)
        }
    }
}