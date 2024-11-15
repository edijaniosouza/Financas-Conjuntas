package com.barrosedijanio.finanasconjuntas.profile.domain.di

import com.barrosedijanio.finanasconjuntas.profile.presentation.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    viewModel <ProfileViewModel>{
        ProfileViewModel(get())
    }
}