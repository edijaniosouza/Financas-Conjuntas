package com.barrosedijanio.finanasconjuntas.firebase.domain.model

import java.util.Date

data class Transaction(
    val paid: Boolean = false,
    val paidDate: Long = Date().time,
    val description: String,
    val value: Float,
    val category: Category,
    val accountType: AccountType = AccountType.CARTEIRA,
    val image: String? = "",
    val repeat: Int = 0,
    val isIncome: Boolean = false,
    val shared: Boolean = false,
    val updatedAt: Long? = Date().time,
)


