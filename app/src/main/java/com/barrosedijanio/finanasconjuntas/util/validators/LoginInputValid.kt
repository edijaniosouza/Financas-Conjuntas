package com.barrosedijanio.finanasconjuntas.util.validators

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
sealed class LoginInputValid: Parcelable {
    data object Valid: LoginInputValid()
    data class Error(val messageId: Int): LoginInputValid()
    data object Empty: LoginInputValid()
}