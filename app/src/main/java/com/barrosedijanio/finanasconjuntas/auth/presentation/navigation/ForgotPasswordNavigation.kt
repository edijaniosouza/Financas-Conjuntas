package com.barrosedijanio.finanasconjuntas.auth.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.auth.presentation.screens.ForgotPasswordScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.ForgotPasswordViewModel
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.forgotPasswordScreen(
    goToVerificationCode: () -> Unit,
    onCancel: () -> Unit,
) {
    composable(Screens.ForgotPassword.route) {
        val viewModel : ForgotPasswordViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        ForgotPasswordScreen(
            uiState = uiState,
            onResetPassword = {
            goToVerificationCode()
        }, onCancel = onCancel)
    }
}