package com.barrosedijanio.finanasconjuntas.auth.domain.di

import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignUpViewModel
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel <SignInViewModel>{
        SignInViewModel(get())
    }
    viewModel <SignUpViewModel>{
        SignUpViewModel(get())
    }
}