package com.barrosedijanio.finanasconjuntas.transactions.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.transactions.presentation.screens.NewExpenseScreen
import com.barrosedijanio.finanasconjuntas.transactions.presentation.screens.NewIncomeScreen
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.NewExpenseViewModel
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.NewIncomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.newIncomeScreen(
    onNavigateBack: () -> Unit
) {
    composable(Screens.Income.route) {
        val viewModel: NewIncomeViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val results by viewModel.result.collectAsState()

        NewIncomeScreen(
            results,
            uiState, onNavigateBack
        ) {
            viewModel.addIncome()
        }
    }
}