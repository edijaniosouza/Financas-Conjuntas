package com.barrosedijanio.finanasconjuntas.firebase.data.transactions

import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction

interface TransactionRepository {

    suspend fun newTransaction(transaction: Transaction)
    suspend fun allTransactions(onCompleteListener: (List<Transaction>) -> Unit,)
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun deleteTransaction(transaction: Transaction)
}

