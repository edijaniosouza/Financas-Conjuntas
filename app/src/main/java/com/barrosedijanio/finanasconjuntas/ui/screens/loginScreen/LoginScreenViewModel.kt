package com.barrosedijanio.finanasconjuntas.ui.screens.loginScreen

import androidx.lifecycle.ViewModel
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.util.emailValidation
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid

class LoginScreenViewModel(

) : ViewModel() {

    fun emailStateValidation(email: String): LoginInputValid{
        return emailValidation(email)
    }

    fun passwordStateValidation(password: String): LoginInputValid{
        return when {
            password.isEmpty() -> {
                LoginInputValid.Error(R.string.empty_field)
            }
            else -> {
                LoginInputValid.Valid
            }
        }
    }
}