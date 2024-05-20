package com.barrosedijanio.finanasconjuntas.ui.screens.createAccountScreen

import androidx.lifecycle.ViewModel
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.util.emailValidation
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid

class CreateAccountScreenViewModel : ViewModel() {


    fun nameStateValidation(name: String): LoginInputValid{
        return when {
            name.isEmpty() -> {
                LoginInputValid.Error(R.string.empty_field)
            }
            name.length <= 3 -> {
                LoginInputValid.Error(R.string.nameTooShort)
            }
            else -> {
                LoginInputValid.Valid
            }
        }
    }
    fun emailStateValidation(email: String): LoginInputValid {
        return emailValidation(email)
    }

    fun passwordStateValidation(password: String): LoginInputValid {
        return when {
            password.isEmpty() -> {
                LoginInputValid.Error(R.string.empty_field)
            }

            password.length < 6 -> {
                LoginInputValid.Error(R.string.password_short)
            }

            else -> {
                LoginInputValid.Valid
            }
        }
    }

    fun confirmPasswordStateValidation(password: String, confirmPassword: String): LoginInputValid {
        val validationConfirmPassword = passwordStateValidation(confirmPassword)

        return if ( validationConfirmPassword == LoginInputValid.Valid) {
            when {
                password != confirmPassword -> {
                    LoginInputValid.Error(R.string.password_not_equal)
                }
                else -> {
                    LoginInputValid.Valid
                }
            }
        } else{
            validationConfirmPassword
        }
    }
}