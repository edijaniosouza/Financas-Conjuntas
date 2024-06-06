package com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.emailValidation
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.NewPasswordUiState
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignUpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewPasswordViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow(NewPasswordUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onPasswordChange = { password ->
                    _uiState.update {
                        it.copy(
                            password = password,
                            passwordState = passwordStateValidation(password)
                        )
                    }
                },
                onConfirmPasswordChange = {confirmPassword ->
                    _uiState.update {
                        it.copy(
                            confirmPassword = confirmPassword,
                            confirmPasswordState = confirmPasswordStateValidation(it.password, confirmPassword)
                        )
                    }
                },
                onPasswordVisibleChange = { visibility ->
                    _uiState.update {
                        it.copy(isPasswordVisible = visibility)
                    }
                },
                onConfirmPasswordVisibleChange = { visibility ->
                    _uiState.update {
                        it.copy(isConfirmPasswordVisible = visibility)
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

    private fun passwordStateValidation(password: String): InputValidResult {
        return when {
            password.isEmpty() -> {
                InputValidResult.Error(R.string.empty_field)
            }

            password.length < 6 -> {
                InputValidResult.Error(R.string.password_short)
            }

            else -> {
                InputValidResult.Valid
            }
        }
    }

    private fun confirmPasswordStateValidation(
        password: String,
        confirmPassword: String
    ): InputValidResult {
        val validationConfirmPassword = passwordStateValidation(confirmPassword)

        return if (validationConfirmPassword == InputValidResult.Valid) {
            when {
                password != confirmPassword -> {
                    InputValidResult.Error(R.string.password_not_equal)
                }

                else -> {
                    InputValidResult.Valid
                }
            }
        } else {
            validationConfirmPassword
        }
    }

}