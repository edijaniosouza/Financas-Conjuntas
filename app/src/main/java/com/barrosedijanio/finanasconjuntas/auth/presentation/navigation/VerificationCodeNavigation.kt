package com.barrosedijanio.finanasconjuntas.auth.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.auth.presentation.screens.VerificationCodeScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.VerificationCodeViewModel
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.verificationCodeScreen(
    goToNewPassword: () -> Unit,
    onCancel: () -> Unit,
) {
    composable(Screens.VerficationCode.route) {
        val viewModel: VerificationCodeViewModel = koinViewModel()
        val uiState by viewModel.uiState.collectAsState()

        VerificationCodeScreen(
            uiState = uiState,
            onVerificationCode = { goToNewPassword() },
            onCancel = onCancel
        )
    }
}