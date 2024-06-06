package com.barrosedijanio.finanasconjuntas.firebase.data.balance

import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Balance
import com.barrosedijanio.finanasconjuntas.home.data.TotalBalanceModel
import kotlinx.coroutines.flow.Flow


interface AccountBalanceRepository {
    fun getBalanceByAccount(accountType: AccountType, onCompleteListener: (Balance) -> Unit)
    fun getTotalBalance() : Flow<TotalBalanceModel>
    fun updateBalance(newValue: Float, isIncome: Boolean, isShared: Boolean, accountType: AccountType)
    fun deleteBalance(balance: Balance)
}