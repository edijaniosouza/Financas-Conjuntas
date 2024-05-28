package com.barrosedijanio.finanasconjuntas.firebase.domain.model

import com.google.firebase.Timestamp
import java.util.Date

data class Transaction(
    val id: Int = Timestamp.now().nanoseconds, // Utilizado como ID
    val paid: Boolean = false,
    val paidDate: Timestamp? = Timestamp.now(),
    val description: String,
    val value: Float,
    val category: Category,
    val accountType: AccountType = AccountType.CARTEIRA,
    val image: String? = "",
    val repeat: Int = 0,
    val isIncome: Boolean = false,
    val shared: Boolean = false,
    val updatedAt: Date = Timestamp.now().toDate(),
)


