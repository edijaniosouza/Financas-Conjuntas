package com.barrosedijanio.finanasconjuntas.home.domain.di

import com.barrosedijanio.finanasconjuntas.home.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel <HomeScreenViewModel>{
        HomeScreenViewModel(get(), get(), get())
    }
}