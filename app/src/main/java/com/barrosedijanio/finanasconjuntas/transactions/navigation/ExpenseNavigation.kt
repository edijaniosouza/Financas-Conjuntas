package com.barrosedijanio.finanasconjuntas.transactions.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.transactions.presentation.screens.NewExpenseScreen
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.NewExpenseViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.newExpenseScreen(
    onNavigateBack: () -> Unit
) {
    composable(Screens.Expense.route) {
        val viewModel: NewExpenseViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        NewExpenseScreen(uiState, onNavigateBack) { transaction ->
            viewModel.addExpense(transaction){
                onNavigateBack()
            }
        }
    }
}