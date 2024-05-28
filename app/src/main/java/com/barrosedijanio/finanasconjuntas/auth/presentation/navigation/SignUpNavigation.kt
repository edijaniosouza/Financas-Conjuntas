package com.barrosedijanio.finanasconjuntas.auth.presentation.navigation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.auth.presentation.screens.SignUpScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel.SignUpViewModel
import com.barrosedijanio.finanasconjuntas.core.navigation.Screens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.signUpScreen(
    navigateToHomeScreen: (username: String) -> Unit,
) {
    composable(Screens.SignUp.route) {
        val viewModel: SignUpViewModel = koinViewModel()
        val responseResult by viewModel.responseResult.collectAsState()
        val uiState by viewModel.uiState.collectAsState()

        Log.i("firebaseAuth", "signUpScreen - Response: $responseResult")

        SignUpScreen(
            uiState,
            onSignUp = { email, password, username ->
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.createUser(email, password, username)
                }
            },
            onSignUpResult = responseResult,
            navigateToHomeScreen = navigateToHomeScreen
        )
    }
}