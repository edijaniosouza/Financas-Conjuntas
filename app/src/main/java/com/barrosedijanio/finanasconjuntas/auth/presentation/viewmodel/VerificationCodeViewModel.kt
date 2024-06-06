package com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.emailValidation
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.VerificationCodeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class VerificationCodeViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow(VerificationCodeUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onCodeChange = { code ->
                    _uiState.update {
                        it.copy(code = code, codeState = codeStateValidation(code))
                    }
                },
                onLoadingChange = { loading ->
                    _uiState.update {
                        it.copy(isLoading = loading)
                    }
                }
            )
        }
    }

    private fun codeStateValidation(code: String): InputValidResult {
        try {
            code.toInt()
            return if (code.length == 6) {
                InputValidResult.Valid
            } else {
                InputValidResult.Error(R.string.invalid_code)
            }
        }catch (e: NumberFormatException){
            return InputValidResult.Error(R.string.code_should_have_only_numbers)
        }
    }
}