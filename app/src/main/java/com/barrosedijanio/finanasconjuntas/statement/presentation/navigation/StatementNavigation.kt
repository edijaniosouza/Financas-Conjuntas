package com.barrosedijanio.finanasconjuntas.statement.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.statement.presentation.screens.StatementScreen
import com.barrosedijanio.finanasconjuntas.statement.presentation.viewmodel.StatementViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.statementScreen() {
    composable(Screens.Statement.route) {
        val viewModel: StatementViewModel = koinViewModel()

        StatementScreen()
    }
}