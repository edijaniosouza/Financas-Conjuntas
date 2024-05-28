package com.barrosedijanio.finanasconjuntas.auth.presentation.states

import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult

data class SignUpUiState(
    val userName: String = "",
    val userNameState : InputValidResult = InputValidResult.Empty,
    val onUserNamechange: (String) -> Unit = {},
    val email: String = "",
    val onEmailChange: (String) -> Unit = {},
    val emailState: InputValidResult = InputValidResult.Empty,
    val password: String = "",
    val onPasswordChange: (String) -> Unit = {},
    val passwordState: InputValidResult = InputValidResult.Empty,
    val confirmPassword : String = "",
    val confirmPasswordState : InputValidResult = InputValidResult.Empty,
    val onConfirmPasswordChange: (String) -> Unit = {},
    val termsAgree : Boolean = false,
    val onTermsChange: (Boolean) -> Unit = {},
    val isPasswordVisible: Boolean = false,
    val onPasswordVisibleChange: (Boolean) -> Unit = {},
    val isLoading: Boolean = false,
    val onLoadingChange: (Boolean) -> Unit = {},
    val error: String? = null
)
