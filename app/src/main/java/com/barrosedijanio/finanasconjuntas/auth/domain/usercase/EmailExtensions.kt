package com.barrosedijanio.finanasconjuntas.auth.domain.usercase

import com.barrosedijanio.finanasconjuntas.R

fun isValidEmailFormat(email: String): Boolean {
    val regexPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]{2,})+$".toRegex()
    return email.matches(regexPattern)
}

fun emailValidation(email: String): InputValidResult {
    return when {
        email.isEmpty() -> {
            InputValidResult.Error(R.string.empty_field)
        }
        isValidEmailFormat(email) -> {
            InputValidResult.Valid
        }
        else -> {
            InputValidResult.Error(R.string.invalid_email)
        }
    }
}