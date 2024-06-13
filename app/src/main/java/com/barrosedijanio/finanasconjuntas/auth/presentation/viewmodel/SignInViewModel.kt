package com.barrosedijanio.finanasconjuntas.auth.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.auth.domain.model.User
import com.barrosedijanio.finanasconjuntas.auth.data.AuthRepositoryImpl
import com.barrosedijanio.finanasconjuntas.auth.domain.model.AuthResult
import com.barrosedijanio.finanasconjuntas.auth.presentation.states.SignInUiState
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.emailValidation
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.data.config.ConfigRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepositoryImpl: AuthRepositoryImpl,
    private val configRepository: ConfigRepository
) : ViewModel() {

    private var _uiState = MutableStateFlow(SignInUiState())
    var uiState = _uiState.asStateFlow()

    private var _authState = MutableStateFlow<AuthResult>(AuthResult.Empty)
    var authState = _authState.asStateFlow()
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
                },
                onLoadingChange = { loading ->
                    _uiState.update {
                        it.copy(isLoading = loading)
                    }
                },
                onErrorChange = { error ->
                    _uiState.update {
                        it.copy(error = error)
                    }
                },
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

    fun signIn(email: String, password: String, remember: Boolean = false) {
        try {
            _authState.value = AuthResult.Loading
            viewModelScope.launch {

                val signIn = authRepositoryImpl.signInWithEmailAndPassword(email, password)
                signIn?.user?.let { firebaseUser ->
                    User(
                        uid = firebaseUser.uid,
                        userEmail = firebaseUser.email!!,
                        username = firebaseUser.displayName!!,
                        profilePictureUrl = firebaseUser.photoUrl,
                    ).apply {
                        configRepository.configNewUser(userId = this.uid!!, remember = remember)

                        _authState.value = AuthResult.OK(this)
                    }
                } ?: run {
                    _authState.value = AuthResult.Error("E-mail ou Senha inválidos")
                }
            }
        } catch (e: Exception) {
            _authState.value = AuthResult.Error("Erro inesperado ao fazer login")
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
                Log.i("firebaseAuth", "signInWithGoogle: response: $response ; user: $user")
                when (response) {
                    is Result.OK -> {
                        Log.i("firebaseAuth", "signInWithGoogle - Dentro: response: $response ; user: $user")

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