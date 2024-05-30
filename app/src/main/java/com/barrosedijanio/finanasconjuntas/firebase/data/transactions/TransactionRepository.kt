package com.barrosedijanio.finanasconjuntas.firebase.data.transactions

import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    fun newTransaction(transaction: Transaction ): Flow<Result>
    suspend fun allTransactions() : Flow<List<Transaction>>
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
}

