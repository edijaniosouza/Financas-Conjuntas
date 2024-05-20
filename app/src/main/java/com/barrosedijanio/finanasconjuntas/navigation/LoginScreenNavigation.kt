package com.barrosedijanio.finanasconjuntas.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.ui.screens.loginScreen.LoginScreen
import com.barrosedijanio.finanasconjuntas.ui.screens.loginScreen.LoginScreenViewModel
import org.koin.androidx.compose.koinViewModel

const val LOGIN_SCREEN = "login"
fun NavGraphBuilder.loginScreen(
    goToCreateAccount: () -> Unit,
) {
    composable(LOGIN_SCREEN) {
        val loginViewModel: LoginScreenViewModel = koinViewModel()


        LoginScreen(
            emailStateValidation = { email ->
                loginViewModel.emailStateValidation(email)
            },
            passwordStateValidation = { password ->
                loginViewModel.passwordStateValidation(password)
            },
            goToGoogleAuth = {
                /*TODO: Google Auth*/
            },
            goToHome = { _, _, _ ->
                /*TODO: Home*/
            },
            goToResetPassword = {
                /*TODO: Reset Password*/

            },
            goToCreateAccount = goToCreateAccount
        )
    }
}