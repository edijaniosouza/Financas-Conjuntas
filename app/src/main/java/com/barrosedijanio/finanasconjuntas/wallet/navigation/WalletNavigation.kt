package com.barrosedijanio.finanasconjuntas.wallet.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.transactions.presentation.screens.NewExpenseScreen
import com.barrosedijanio.finanasconjuntas.transactions.presentation.screens.NewIncomeScreen
import com.barrosedijanio.finanasconjuntas.transactions.presentation.screens.TransferScreen
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.NewExpenseViewModel
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.NewIncomeViewModel
import com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel.TransferViewModel
import com.barrosedijanio.finanasconjuntas.wallet.presentation.screens.WalletScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.walletScreen() {
    composable(Screens.Wallet.route) {
        WalletScreen()
    }
}