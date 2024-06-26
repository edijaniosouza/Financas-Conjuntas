package com.barrosedijanio.finanasconjuntas.auth.domain.di

import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.ForgotPasswordViewModel
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.NewPasswordViewModel
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignUpViewModel
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignInViewModel
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.VerificationCodeViewModel
import com.barrosedijanio.finanasconjuntas.core.viewmodel.ConfigViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel<SignInViewModel> {
        SignInViewModel(get(), get())
    }
    viewModel<SignUpViewModel> {
        SignUpViewModel(get())
    }
    viewModel<ForgotPasswordViewModel> {
        ForgotPasswordViewModel(get())
    }

    viewModel<VerificationCodeViewModel> {
        VerificationCodeViewModel(get())
    }
    viewModel<ConfigViewModel> {
        ConfigViewModel(get())
    }

    viewModel<NewPasswordViewModel> {
        NewPasswordViewModel(get())
    }
}