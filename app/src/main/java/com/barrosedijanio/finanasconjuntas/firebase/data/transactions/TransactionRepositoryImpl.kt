package com.barrosedijanio.finanasconjuntas.firebase.data.transactions

import android.util.Log
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.core.helpers.stringToTimestamp
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.AccountBalanceRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.FIREBASEDATA
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

const val DATA = "data"
const val TRANSACTIONS = "transactions"


class TransactionRepositoryImpl(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth,
    private val balanceRepository: AccountBalanceRepositoryImpl
) : TransactionRepository {

    private val dataCollection = firestore.collection(DATA)
    private val userId = auth.currentUser?.uid
    private val userIdDocument = userId?.let { dataCollection.document(it) }

    override fun newTransaction(transaction: Transaction) = flow {
        emit(Result.Loading)

        balanceRepository.updateBalance(
            transaction.value,
            transaction.isIncome,
            transaction.shared,
            transaction.accountType
        )

        createInFirestore(
            model = transaction,
            collection = TRANSACTIONS,
        ).collect { result ->
            result?.let {
                emit(it)
            }
        }
    }

    override suspend fun allTransactions() = flow<List<Transaction>> {
        val transactions = mutableListOf<Transaction>()
        val list = userIdDocument?.collection(TRANSACTIONS)?.orderBy("paidDate")
            ?.get()?.await()

        list?.documents?.forEach {
            transactions.add(
                documentSnapshotToTransaction(it)
            )
            emit(transactions)
        }
    }

    fun getTransactionByType(isIncome: Boolean) {
        userIdDocument?.collection(TRANSACTIONS)?.whereEqualTo("income", isIncome)?.get()
            ?.addOnCompleteListener { querySnapshot ->
                if (querySnapshot.isSuccessful) {
                    Log.i(FIREBASEDATA, "getTransactionByType: ${querySnapshot.result.documents}")
                    querySnapshot.result.forEach { data ->
                        Log.i(
                            FIREBASEDATA,
                            "getTransactionByType: id: ${data["id"]} - value: ${data["value"]}"
                        )
                    }
                }
            }
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    private fun createInFirestore(
        model: Any,
        collection: String,
    ) = flow {
        emit(Result.Loading)
        try {
            userIdDocument
                ?.collection(collection)?.add(model)?.await()
            emit(Result.OK)
        } catch (e: CancellationException) {
            emit(e.message?.let { Result.Error(it) })
        }
    }

    private fun documentSnapshotToTransaction(data: DocumentSnapshot): Transaction {
        val newTransaction: Transaction
        try {
            newTransaction = Transaction(
                id = data["id"].toString().toInt(),
                category = Category(
                    icon = data["category.icon"].toString().toInt(),
                    name = data["category.name"].toString()
                ),
                description = data["description"].toString(),
                value = data["value"].toString().toFloat(),
                isIncome = data["income"].toString().toBoolean(),
                paid = data["paid"].toString().toBoolean(),
//                paidDate = stringToTimestamp(data["paidDate"].toString()),
                accountType = AccountType.valueOf(data["accountType"].toString())
            )
        } catch (e: Exception) {
            throw IllegalArgumentException("Erro ao converter o objeto Transaction")
        }
        return newTransaction
    }
}
