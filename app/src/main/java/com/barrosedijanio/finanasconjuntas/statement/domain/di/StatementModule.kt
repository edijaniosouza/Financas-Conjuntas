package com.barrosedijanio.finanasconjuntas.statement.domain.di

import com.barrosedijanio.finanasconjuntas.statement.presentation.viewmodel.StatementViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val statementModule = module {
    viewModel <StatementViewModel>{
        StatementViewModel(get())
    }
}