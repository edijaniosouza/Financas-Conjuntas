package com.barrosedijanio.finanasconjuntas.firebase.data.balance

import android.util.Log
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.DATA
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Balance
import com.barrosedijanio.finanasconjuntas.home.data.TotalBalanceModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

const val BALANCE = "balance"

const val FIREBASEDATA = "firebaseData"

class AccountBalanceRepositoryImpl(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth
) : AccountBalanceRepository {
    private val dataCollection = firestore.collection(DATA)
    private val userId = auth.currentUser?.uid
    private val userIdDocument = userId?.let { dataCollection.document(it) }
    override fun getBalanceByAccount(
        accountType: AccountType,
        onCompleteListener: (balance: Balance) -> Unit
    ) {
        userIdDocument?.collection(BALANCE)
            ?.document(accountType.name)
            ?.get()
            ?.addOnCompleteListener {
                if (it.isSuccessful) {

                    val result = it.result.data.let { data ->
                        Balance(
                            balance = data?.get("balance")?.toString()?.toFloat() ?: 0f,
                            expenseBalance = data?.get("expenseBalance")?.toString()?.toFloat()
                                ?: 0f,
                            incomeBalance = data?.get("incomeBalance")?.toString()?.toFloat() ?: 0f,
                            shareBalance = data?.get("shareBalance")?.toString()?.toFloat() ?: 0f
                        )
                    }

                    onCompleteListener(result)
                }
            }
    }

    override fun getTotalBalance(): Flow<TotalBalanceModel> = flow {
        try {
            val docs = userIdDocument?.collection(BALANCE)?.get()?.await()

            val balance = mutableListOf<Float>()
            val expense = mutableListOf<Float>()
            val income = mutableListOf<Float>()
            val share = mutableListOf<Float>()

            docs?.forEach { documentSnapshot ->
                balance.add(
                    documentSnapshot.data["balance"].toString().toFloat()
                )
                expense.add(
                    documentSnapshot.data["expenseBalance"].toString().toFloat()
                )
                income.add(
                    documentSnapshot.data["incomeBalance"].toString().toFloat()
                )
                share.add(
                    documentSnapshot.data["shareBalance"].toString().toFloat()
                )
            }

            Log.e("firebaseAuth", "balance value: ${balance.isEmpty()}")

            if (balance.isNotEmpty()) {
                val totalBalance = balance.reduce { acc, fl -> acc + fl }
                val totalExpense = expense.reduce { acc, fl -> acc + fl }
                val totalIncome = income.reduce { acc, fl -> acc + fl }
                val totalShare = share.reduce { acc, fl -> acc + fl }

                emit(TotalBalanceModel(totalBalance, totalExpense, totalIncome, totalShare))
            } else {
                emit(TotalBalanceModel(0f, 0f, 0f, 0f))
            }
        } catch (e: Exception) {
            Log.e("firebaseAuth", e.message.toString())
        }
    }

    override fun updateBalance(
        newValue: Float,
        isIncome: Boolean,
        isShared: Boolean,
        accountType: AccountType
    ) {
        getBalanceByAccount(accountType = accountType) { balance ->
            userIdDocument?.collection(BALANCE)?.document(accountType.name)?.set(
                if (!isIncome) {
                    balance.copy(
                        balance = balance.balance - newValue,
                        expenseBalance = balance.expenseBalance + newValue
                    )
                } else {
                    balance.copy(
                        balance = balance.balance + newValue,
                        incomeBalance = balance.incomeBalance + newValue
                    )
                }
            )
        }
    }

    private fun updateBalanceWithoutDiscount(
        newValue: Float,
        isIncome: Boolean,
        accountType: AccountType
    ) {
        getBalanceByAccount(accountType = accountType) { balance ->
            userIdDocument?.collection(BALANCE)?.document(accountType.name)?.set(
                if (!isIncome) {
                    balance.copy(
                        balance = balance.balance - newValue,
                    )
                } else {
                    balance.copy(
                        balance = balance.balance + newValue,
                    )
                }
            )
        }
    }

    fun transferValueBetweenAccounts(from: AccountType, to: AccountType, value: Float) {
        updateBalanceWithoutDiscount(newValue = value, isIncome = false, accountType = from)
        updateBalanceWithoutDiscount(newValue = value, isIncome = true, accountType = to)
    }

    override fun deleteBalance(balance: Balance) {
        TODO("Not yet implemented")
    }

}