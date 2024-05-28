package com.barrosedijanio.finanasconjuntas.home.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import com.barrosedijanio.finanasconjuntas.home.presentation.screens.HomeScreen
import com.barrosedijanio.finanasconjuntas.home.presentation.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeScreen() {
    composable(Screens.Home.route) {
        val viewModel: HomeScreenViewModel = koinViewModel()
        val list by viewModel.list.collectAsState()

        HomeScreen(list){
            viewModel.readData()
        }
    }
}