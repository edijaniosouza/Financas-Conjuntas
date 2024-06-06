package com.barrosedijanio.finanasconjuntas.statement.presentation.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.statement.presentation.screens.StatementScreen
import com.barrosedijanio.finanasconjuntas.statement.presentation.viewmodel.StatementViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.statementScreen() {
    composable(Screens.Statement.route) {
        val viewModel: StatementViewModel = koinViewModel()
        val transactions by viewModel.transactions.collectAsState()
        val lifecycleOwner = LocalLifecycleOwner.current

        LaunchedEffect(key1 = lifecycleOwner) {
            viewModel.getTransactions()
        }

        StatementScreen(transactions){
            viewModel.getTransactionByFilter(it)
        }
    }
}