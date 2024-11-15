package com.barrosedijanio.finanasconjuntas.firebase.data.transactions

import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.AccountBalanceRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.Date

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
        val list = userIdDocument?.collection(TRANSACTIONS)?.orderBy("paidDate", Query.Direction.DESCENDING)
            ?.get()?.await()

        list?.documents?.forEach {
            transactions.add(
                documentSnapshotToTransaction(it)
            )
            emit(transactions)
        }
    }

    override suspend fun getTransactionByType(isIncome: Boolean) = flow<List<Transaction>> {
        val transactions = mutableListOf<Transaction>()
        val list = userIdDocument?.collection(TRANSACTIONS)?.whereEqualTo("income", isIncome)?.get()
                ?.await()

        list?.documents?.forEach {
            transactions.add(
                documentSnapshotToTransaction(it)
            )
            emit(transactions)
        }
    }

    override suspend fun getTransactionByPeriod(period: Long): Flow<List<Transaction>> = flow {
        val transactions = mutableListOf<Transaction>()
        val days = 86400000*period
        val limitDate = Date().time - days

        val list = userIdDocument?.collection(TRANSACTIONS)
            ?.orderBy("paidDate", Query.Direction.DESCENDING)
            ?.whereGreaterThan("paidDate", limitDate)
            ?.get()
            ?.await()

        list?.documents?.forEach {
            transactions.add(
                documentSnapshotToTransaction(it)
            )
            emit(transactions)
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
            emit(Result.OK())
        } catch (e: CancellationException) {
            emit(e.message?.let { Result.Error(it) })
        }
    }

    private fun documentSnapshotToTransaction(data: DocumentSnapshot): Transaction {
        val newTransaction: Transaction
        try {
            newTransaction = Transaction(
                category = Category(
                    icon = data["category.icon"].toString().toInt(),
                    name = data["category.name"].toString()
                ),
                description = data["description"].toString(),
                value = data["value"].toString().toFloat(),
                isIncome = data["income"].toString().toBoolean(),
                paid = data["paid"].toString().toBoolean(),
                paidDate = data["paidDate"].toString().toLong(),
                accountType = AccountType.valueOf(data["accountType"].toString())
            )
        } catch (e: Exception) {
            throw IllegalArgumentException("Erro ao converter o objeto Transaction")
        }
        return newTransaction
    }
}
