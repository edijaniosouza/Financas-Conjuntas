package com.barrosedijanio.finanasconjuntas.auth.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.auth.presentation.screens.ForgotPasswordScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.screens.NewPasswordScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.NewPasswordViewModel
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.newPasswordScreen(
    goToHome: () -> Unit,
    onCancel: () -> Unit,
) {
    composable(Screens.NewPassword.route) {
        val viewModel: NewPasswordViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        NewPasswordScreen(uiState = uiState, onNewPassword = { goToHome() }, onCancel = onCancel)
    }
}