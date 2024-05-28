package com.barrosedijanio.finanasconjuntas.auth.domain.usercase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
sealed class InputValidResult: Parcelable {
    data object Valid: InputValidResult()
    data class Error(val messageId: Int): InputValidResult()
    data object Empty: InputValidResult()
}