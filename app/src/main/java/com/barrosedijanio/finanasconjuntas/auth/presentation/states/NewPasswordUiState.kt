package com.barrosedijanio.finanasconjuntas.auth.presentation.states

import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult

data class NewPasswordUiState(
    val password: String = "",
    val onPasswordChange: (String) -> Unit = {},
    val passwordState: InputValidResult = InputValidResult.Empty,
    val confirmPassword : String = "",
    val confirmPasswordState : InputValidResult = InputValidResult.Empty,
    val onConfirmPasswordChange: (String) -> Unit = {},
    val isPasswordVisible: Boolean = false,
    val onPasswordVisibleChange: (Boolean) -> Unit = {},
    val isConfirmPasswordVisible: Boolean = false,
    val onConfirmPasswordVisibleChange: (Boolean) -> Unit = {},
    val isLoading: Boolean = false,
    val onLoadingChange: (Boolean) -> Unit = {},
    val error: String? = null
)
