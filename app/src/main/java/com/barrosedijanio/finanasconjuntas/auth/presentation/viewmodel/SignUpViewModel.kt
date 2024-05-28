package com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.emailValidation
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignUpUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {
    private var _uiState = MutableStateFlow(SignUpUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onEmailChange = { email ->
                    _uiState.update {
                        it.copy(email = email, emailState = emailStateValidation(email))
                    }
                },
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
                onUserNamechange = { name ->
                    _uiState.update {
                        it.copy(userName = name, userNameState = nameStateValidation(name))
                    }
                },
                onTermsChange = {terms ->
                    _uiState.update {
                        it.copy(termsAgree = terms)
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

    fun nameStateValidation(name: String): InputValidResult {
        return when {
            name.isEmpty() -> {
                InputValidResult.Error(R.string.empty_field)
            }

            name.length <= 3 -> {
                InputValidResult.Error(R.string.nameTooShort)
            }

            else -> {
                InputValidResult.Valid
            }
        }
    }

    fun emailStateValidation(email: String): InputValidResult {
        return emailValidation(email)
    }

    fun passwordStateValidation(password: String): InputValidResult {
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

    fun confirmPasswordStateValidation(
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

    private val _Result = MutableStateFlow<Result>(Result.Empty)
    val responseResult = _Result.asStateFlow()
    fun createUser(email: String, password: String, username: String) {
        authRepositoryImpl.signUpWithEmailAndPassword(email, password, username){
            _Result.value = it
        }
    }
}