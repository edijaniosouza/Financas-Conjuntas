package com.barrosedijanio.finanasconjuntas.transactions.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.transactions.presentation.screens.TransferScreen
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.TransferViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.transferScreen(
    onNavigateBack: () -> Unit
) {
    composable(Screens.Transfer.route) {
        val viewModel: TransferViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val results by viewModel.result.collectAsState()

        TransferScreen(
            results,
            uiState, onNavigateBack
        ) {
            viewModel.transferValueBetweenAccounts()
        }
    }
}