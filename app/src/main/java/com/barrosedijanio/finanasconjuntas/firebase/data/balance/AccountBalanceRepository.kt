package com.barrosedijanio.finanasconjuntas.firebase.data.balance

import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Balance


interface AccountBalanceRepository {
    fun getBalance(accountType: AccountType, onCompleteListener: (Balance) -> Unit)
    fun updateBalance(newValue: Float, isIncome: Boolean, isShared: Boolean, accountType: AccountType)
    fun deleteBalance(balance: Balance)
}