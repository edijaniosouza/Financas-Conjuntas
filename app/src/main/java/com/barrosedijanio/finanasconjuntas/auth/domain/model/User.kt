package com.barrosedijanio.finanasconjuntas.auth.domain.model

import android.net.Uri

data class User(
    val uid: String?,
    val username: String?,
    val userEmail: String?,
    val profilePictureUrl: Uri?,
)
