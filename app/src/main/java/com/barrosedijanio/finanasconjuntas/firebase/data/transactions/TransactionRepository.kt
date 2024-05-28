package com.barrosedijanio.finanasconjuntas.firebase.data.transactions

import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction

interface TransactionRepository {

    fun newTransaction(transaction: Transaction)
    fun allTransactions(onCompleteListener: (List<Transaction>) -> Unit,)
    fun updateTransaction(transaction: Transaction)
    fun deleteTransaction(transaction: Transaction)
}

