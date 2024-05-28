package com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.model.User
import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignInUiState
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.emailValidation
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

    private var _uiState = MutableStateFlow(SignInUiState())
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
                onPasswordVisibleChange = { visibility ->
                    _uiState.update {
                        it.copy(isPasswordVisible = visibility)
                    }
                },
                onRememberChange = { remember ->
                    _uiState.update {
                        it.copy(rememberMe = remember)
                    }
                }
            )
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

            else -> {
                InputValidResult.Valid
            }
        }
    }

    fun signIn(email: String, password: String, onLoginSuccess: (User) -> Unit) {
        try {
            viewModelScope.launch {
                val signIn = authRepositoryImpl.signInWithEmailAndPassword(email, password)
                val user = signIn?.user?.let { firebaseUser ->
                    User(
                        uid = firebaseUser.uid,
                        userEmail = firebaseUser.email!!,
                        username = firebaseUser.displayName!!,
                        profilePictureUrl = firebaseUser.photoUrl,
                    )
                }
                onLoginSuccess(user!!)
                Log.i("firebaseAuth", "signIn: ${signIn?.user?.displayName}")
            }
        } catch (e: Exception) {
            Log.e("SignInViewModel", "signIn: ${e.message}")
        }
    }

    fun signInWithGoogle(context: Context, goToHome: (User) -> Unit) {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            authRepositoryImpl.signInWithGoogle(context) { response, user ->
                when (response) {
                    is Result.OK -> {
                        _uiState.update { it.copy(isLoading = false) }
                        if (user != null) {
                                    User(
                                        uid = user.uid,
                                        username = user.displayName,
                                        userEmail = user.email,
                                        profilePictureUrl = user.photoUrl,
                                    )
                            .let { userConverted -> goToHome(userConverted)
                            }
                        }
                    }
                    else -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = "Falha ao carregar google autentication"
                            )
                        }
                    }
                }

            }
        }
    }
}