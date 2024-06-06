package com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.emailValidation
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.ForgotPasswordUiState
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignUpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ForgotPasswordViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow(ForgotPasswordUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { email ->
                    _uiState.update {
                        it.copy(email = email, emailState = emailStateValidation(email))
                    }
                },
                onLoadingChange = {loading ->
                    _uiState.update {
                        it.copy(isLoading = loading)
                    }
                }
            )
        }
    }

    private fun emailStateValidation(email: String): InputValidResult {
        return emailValidation(email)
    }

    private val _result = MutableStateFlow<Result>(Result.Empty)
    val responseResult = _result.asStateFlow()
}