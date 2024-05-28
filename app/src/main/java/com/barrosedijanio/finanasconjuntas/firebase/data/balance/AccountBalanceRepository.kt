package com.barrosedijanio.finanasconjuntas.firebase.data.balance

import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Balance


interface AccountBalanceRepository {
    fun getBalance(onCompleteListener: (Balance) -> Unit)
    fun updateBalance(newValue: Float, isIncome: Boolean, isShared: Boolean)
    fun deleteBalance(balance: Balance)
}