package com.barrosedijanio.finanasconjuntas.util

import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid

fun isValidEmailFormat(email: String): Boolean {
    val regexPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]{2,})+$".toRegex()
    return email.matches(regexPattern)
}

fun emailValidation(email: String): LoginInputValid {
    return when {
        email.isEmpty() -> {
            LoginInputValid.Error(R.string.empty_field)
        }
        isValidEmailFormat(email) -> {
            LoginInputValid.Valid
        }
        else -> {
            LoginInputValid.Error(R.string.invalid_email)
        }
    }
}