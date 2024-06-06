package com.barrosedijanio.finanasconjuntas.auth.presentation.states

import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult

data class ForgotPasswordUiState(
    val email: String = "",
    val onEmailChange: (String) -> Unit = {},
    val emailState: InputValidResult = InputValidResult.Empty,
    val isLoading: Boolean = false,
    val onLoadingChange: (Boolean) -> Unit = {},
    val error: String? = null
)
