package com.barrosedijanio.finanasconjuntas.home.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.home.presentation.screens.HomeScreen
import com.barrosedijanio.finanasconjuntas.home.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeScreen(
) {
    composable(Screens.Home.route) {
        val viewModel: HomeScreenViewModel = koinViewModel()
        val balance by viewModel.balance.collectAsState()
        val userUrl by viewModel.userUrl.collectAsState()

        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(key1 = lifecycleOwner) {
            viewModel.loadBalance()
        }
        HomeScreen(balance)
    }
}