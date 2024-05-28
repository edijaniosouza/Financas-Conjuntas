package com.barrosedijanio.finanasconjuntas.auth.presentation.states

import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult

data class SignInUiState(
    val email: String = "",
    val onEmailChange: (String) -> Unit = {},
    val emailState: InputValidResult = InputValidResult.Empty,
    val password: String = "",
    val onPasswordChange: (String) -> Unit = {},
    val passwordState: InputValidResult = InputValidResult.Empty,
    val rememberMe: Boolean = false,
    val onRememberChange: (Boolean) -> Unit = {},
    val isPasswordVisible: Boolean = false,
    val onPasswordVisibleChange: (Boolean) -> Unit = {},
    val isLoading: Boolean = false,
    val error: String? = null
)
