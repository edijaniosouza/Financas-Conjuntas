package com.barrosedijanio.finanasconjuntas.home.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.AccountBalanceRepository
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.AccountBalanceRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepository
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Balance
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val databaseRepo: TransactionRepositoryImpl,
    private val balanceRepo: AccountBalanceRepositoryImpl
) : ViewModel() {

//    private var _uiState = MutableStateFlow(HomeScreenUiState())
//    var uiState = _uiState.asStateFlow()

    private var _list = MutableStateFlow<List<Transaction>>(listOf())
    var list = _list.asStateFlow()
    fun readData() {
        try {
//            databaseRepo.allTransactions { list ->
//                _list.value = list
//                Log.i("firebaseData", "viewmodel - readData: ${_list.value}")
//            }

        } catch (e: Exception) {
            Log.e("firebaseError", "viewmodel - readData: ${e.message}")
        }
    }

    fun getAccountBalance() {
        viewModelScope.launch {
            balanceRepo.getBalance(AccountType.CARTEIRA) {}
        }
    }

    fun updateBalance() {
//        balanceRepo.updateBalance(
//            Balance(
//                balance = 60f,
//                incomeBalance = 0.00f,
//                expenseBalance = 0.00f,
//                shareBalance = 0.00f,
//            )
//        )
    }

    fun getDataByDate() {
        try {
            _list.value = _list.value.sortedBy {
                it.paidDate
            }
            Log.i("firebaseData", "getDataByDate: ${_list.value}")

        } catch (e: Exception) {
            Log.e("firebaseError", "readData: ${e.message}")
        }
    }

    fun createData() {
        val transaction = Transaction(
            category = Category("Casa", R.drawable.google),
            description = "moto",
            value = 150.53f,
            isIncome = true,
        )
        viewModelScope.launch {
            databaseRepo.newTransaction(transaction)
        }
//        databaseRepo.newIncome(transaction)
    }
}
// moto