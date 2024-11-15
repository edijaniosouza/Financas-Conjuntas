package com.barrosedijanio.finanasconjuntas.firebase.domain.model

import com.google.firebase.Timestamp

data class RequestModel(
    val senderId: String,
    val receiverId: String? = null,
    val status: LinkRequestStatus = LinkRequestStatus.PENDING,
    val createdAt: Timestamp = Timestamp.now(),
    val updatedAt: Timestamp = Timestamp.now()
)
