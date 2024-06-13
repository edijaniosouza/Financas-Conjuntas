package com.barrosedijanio.finanasconjuntas.auth.domain.model

sealed class AuthResult{
    data class OK(val user: User): AuthResult()

    data object Loading : AuthResult()
    data class Error(val errorMessage: String) : AuthResult()
    data object Empty : AuthResult()
}
