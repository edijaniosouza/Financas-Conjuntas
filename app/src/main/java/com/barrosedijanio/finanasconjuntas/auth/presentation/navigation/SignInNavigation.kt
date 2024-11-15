package com.barrosedijanio.finanasconjuntas.auth.presentation.navigation

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.barrosedijanio.finanasconjuntas.auth.presentation.screens.SignInScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignInViewModel
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.signInScreen(
    goToCreateAccount: () -> Unit,
    goToHome: () -> Unit,
    goToResetPassword: (email: String) -> Unit
) {
    composable(Screens.SignIn.route) {
        val loginViewModel: SignInViewModel = koinViewModel()
        val uiState by loginViewModel.uiState.collectAsState()
        val authState by loginViewModel.authState.collectAsState()

        SignInScreen(
            uiState = uiState,
            authState = authState,
            goToGoogleAuth = { context ->
                loginViewModel.signInWithGoogle(context) { _ ->
                    goToHome()
                }
            },
            onLogin = { email, password, _ ->
                loginViewModel.signIn(email, password)
            },
            goToHomeScreen = goToHome,
            goToResetPassword = { email ->
                goToResetPassword(email)
            },
            goToCreateAccount = goToCreateAccount
        )
    }
}