package com.barrosedijanio.finanasconjuntas.core.generics

sealed class Result {
    data class OK(val value: String? = null): Result()
    data object Loading: Result()
    data class Error(val errorMessage: String): Result()
    data object Empty: Result()
}
