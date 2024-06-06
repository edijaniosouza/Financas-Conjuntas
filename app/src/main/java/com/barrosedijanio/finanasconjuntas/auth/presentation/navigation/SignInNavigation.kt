package com.barrosedijanio.finanasconjuntas.auth.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.auth.domain.model.User
import com.barrosedijanio.finanasconjuntas.auth.presentation.screens.SignInScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignInViewModel
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.signInScreen(
    goToCreateAccount: () -> Unit,
    goToHome: () -> Unit,
    goToResetPassword: (email: String) -> Unit
) {
    composable(Screens.SignIn.route) {
        val loginViewModel: SignInViewModel = koinViewModel()
        val uiState by loginViewModel.uiState.collectAsState()

        SignInScreen(
            uiState = uiState,
            goToGoogleAuth = { context ->
                loginViewModel.signInWithGoogle(context) { _ ->
                    goToHome()
                }
            },
            goToHome = { email, password, _ ->
                loginViewModel.signIn(email, password) { _ ->
                    goToHome()
                }
            },
            goToResetPassword = { email ->
                goToResetPassword(email)
            },
            goToCreateAccount = goToCreateAccount
        )
    }
}