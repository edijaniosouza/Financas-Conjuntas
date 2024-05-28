package com.barrosedijanio.finanasconjuntas.firebase.data.transactions

import android.util.Log
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.AccountBalanceRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.FIREBASEDATA
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
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
    private val timeNow: Date = Timestamp.now().toDate()
    override fun newTransaction(transaction: Transaction) {

        if (transaction.isIncome) createIncome(transaction) else createExpense(transaction)
//        createInFirestore(
//            timeNow = timeNow,
//            model = transaction,
//            collection = TRANSACTIONS,
//            onCompleteListener = {},
//            onFailureListener = {})
    }

    fun createIncome(transaction: Transaction){
        balanceRepository.updateBalance(transaction.value, transaction.isIncome, transaction.shared)

        createInFirestore(
            timeNow = timeNow,
            model = transaction,
            collection = TRANSACTIONS,
            onCompleteListener = {},
            onFailureListener = {})

    }

    fun createExpense(transaction: Transaction){

    }

    override fun allTransactions(
        onCompleteListener: (List<Transaction>) -> Unit,
    ) {
        getTransactionByType(true)
        val transactions = mutableListOf<Transaction>()
        userIdDocument?.collection(TRANSACTIONS)?.orderBy("paidDate")
            ?.get()
            ?.addOnCompleteListener { querySnapshot ->
                if (querySnapshot.isSuccessful) {
                    querySnapshot.result.documents.forEach { data ->
                        transactions.add(
                            documentSnapshotToTransaction(data)
                        )
                    }
                    onCompleteListener(transactions)
                }
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

    override fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    private fun createInFirestore(
        timeNow: Date,
        model: Any,
        collection: String,
        onCompleteListener: (Task<Void>) -> Unit,
        onFailureListener: (Exception) -> Unit,
    ) {
        userIdDocument
            ?.collection(collection)?.add(model)
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
                isIncome = data["income"].toString().toBoolean()
            )
        } catch (e: Exception) {
            throw IllegalArgumentException("Erro ao converter o objeto Transaction")
        }
        return newTransaction
    }
}
