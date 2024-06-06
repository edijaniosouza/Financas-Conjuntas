package com.barrosedijanio.finanasconjuntas.auth.presentation.states

import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult

data class VerificationCodeUiState(
    val code: String = "",
    val onCodeChange: (String) -> Unit = {},
    val codeState: InputValidResult = InputValidResult.Empty,
    val isLoading: Boolean = false,
    val onLoadingChange: (Boolean) -> Unit = {},
    val error: String? = null
)
