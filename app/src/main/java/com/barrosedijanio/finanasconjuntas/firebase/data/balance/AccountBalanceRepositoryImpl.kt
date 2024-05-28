package com.barrosedijanio.finanasconjuntas.firebase.data.balance

import android.util.Log
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.DATA
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
    override fun getBalance(onCompleteListener: (balance: Balance) -> Unit) {
        userIdDocument?.collection(BALANCE)
            ?.document("userBalance")
            ?.get()
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    val data = it.result.data!!
                    val result = Balance(
                        balance = data["balance"]?.toString()!!.toFloat(),
                        expenseBalance = data["expenseBalance"]?.toString()!!.toFloat(),
                        incomeBalance = data["incomeBalance"]?.toString()!!.toFloat(),
                        shareBalance = data["shareBalance"]?.toString()!!.toFloat()
                    )

                    onCompleteListener(result)
                    Log.i(FIREBASEDATA, "getBalance: $result")
                }
            }
    }

    override fun updateBalance(newValue: Float, isIncome: Boolean, isShared: Boolean) {
        getBalance {
            it.balance

            val new = it.copy(balance = it.balance + newValue)
            userIdDocument?.collection(BALANCE)?.document("userBalance")?.set(
                new
            )
        }
    }

    override fun deleteBalance(balance: Balance) {
        TODO("Not yet implemented")
    }

}