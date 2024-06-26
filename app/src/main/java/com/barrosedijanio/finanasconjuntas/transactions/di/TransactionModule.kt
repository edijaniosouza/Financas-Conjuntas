package com.barrosedijanio.finanasconjuntas.transactions.di

import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.NewExpenseViewModel
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.NewIncomeViewModel
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.TransferViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val transactionsModule = module {
    viewModel <NewExpenseViewModel>{
        NewExpenseViewModel(get())
    }
    viewModel <NewIncomeViewModel>{
        NewIncomeViewModel(get())
    }
    viewModel <TransferViewModel>{
        TransferViewModel(get())
    }
}