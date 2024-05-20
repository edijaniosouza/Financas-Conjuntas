package com.barrosedijanio.finanasconjuntas.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.ui.screens.createAccountScreen.CreateAccountScreen
import com.barrosedijanio.finanasconjuntas.ui.screens.createAccountScreen.CreateAccountScreenViewModel
import org.koin.androidx.compose.koinViewModel

const val CREATE_ACCOUNT_ROUTE = "create_account"
fun NavGraphBuilder.createAccountScreen() {
    composable(CREATE_ACCOUNT_ROUTE) {
        val viewModel: CreateAccountScreenViewModel = koinViewModel()

        CreateAccountScreen(
            nameStateValidation = {name -> viewModel.nameStateValidation(name)},
            emailStateValidation = { email -> viewModel.emailStateValidation(email) },
            passwordStateValidation = { password -> viewModel.passwordStateValidation(password) },
            confirmPasswordStateValidation = { password, confirmPassword ->
                viewModel.confirmPasswordStateValidation(
                    password,
                    confirmPassword
                )
            })
    }
}