package com.barrosedijanio.finanasconjuntas.firebase.data.balance

import android.util.Log
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.DATA
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Balance
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

const val BALANCE = "balance"

const val FIREBASEDATA = "firebaseData"

class AccountBalanceRepositoryImpl(
    firestore: FirebaseFirestore,
    auth: FirebaseAuth
) : AccountBalanceRepository {
    private val dataCollection = firestore.collection(DATA)
    private val userId = auth.currentUser?.uid
    private val userIdDocument = userId?.let { dataCollection.document(it) }
    override fun getBalance(accountType: AccountType, onCompleteListener: (balance: Balance) -> Unit) {
        userIdDocument?.collection(BALANCE)
            ?.document(accountType.name)
            ?.get()
            ?.addOnCompleteListener {
                if (it.isSuccessful) {

                    val result = it.result.data.let {data ->
                        Balance(
                            balance = data?.get("balance")?.toString()?.toFloat() ?: 0f,
                            expenseBalance = data?.get("expenseBalance")?.toString()?.toFloat()?: 0f,
                            incomeBalance = data?.get("incomeBalance")?.toString()?.toFloat()?: 0f,
                            shareBalance = data?.get("shareBalance")?.toString()?.toFloat()?: 0f
                        )
                    }


                    onCompleteListener(result)
                    Log.i(FIREBASEDATA, "getBalance: $result")
                }
            }
    }

    override fun updateBalance(newValue: Float, isIncome: Boolean, isShared: Boolean, accountType: AccountType) {
        getBalance(accountType = accountType) {

            userIdDocument?.collection(BALANCE)?.document(accountType.name)?.set(
                if(!isIncome){
                    it.copy(balance = it.balance - newValue, expenseBalance = it.expenseBalance + newValue)
                } else {
                    it.copy(balance = it.balance + newValue, incomeBalance = it.incomeBalance + newValue)
                }
            )

        }
    }

    override fun deleteBalance(balance: Balance) {
        TODO("Not yet implemented")
    }

}