package com.barrosedijanio.finanasconjuntas.core.generics

sealed class Result {
    data object OK: Result()
    data object Loading: Result()
    data class Error(val errorMessage: String): Result()
    data object Empty: Result()
}
