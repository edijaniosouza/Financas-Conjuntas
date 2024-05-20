package com.barrosedijanio.finanasconjuntas.di.viewmodel

import com.barrosedijanio.finanasconjuntas.ui.screens.createAccountScreen.CreateAccountScreenViewModel
import com.barrosedijanio.finanasconjuntas.ui.screens.loginScreen.LoginScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel <LoginScreenViewModel>{
        LoginScreenViewModel()
    }
    viewModel <CreateAccountScreenViewModel>{
        CreateAccountScreenViewModel()
    }
}