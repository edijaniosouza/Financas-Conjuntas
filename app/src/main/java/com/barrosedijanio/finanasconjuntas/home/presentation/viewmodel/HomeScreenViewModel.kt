package com.barrosedijanio.finanasconjuntas.home.presentation.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.firebase.data.accountLink.AccountLinkRepository
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.AccountBalanceRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.home.data.TotalBalanceModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val databaseRepo: TransactionRepositoryImpl,
    private val balanceRepo: AccountBalanceRepositoryImpl,
    private val linkRepo: AccountLinkRepository,
) : ViewModel() {

    private var _list = MutableStateFlow<List<Transaction>>(listOf())
    var list = _list.asStateFlow()

    private var _balance = MutableStateFlow<TotalBalanceModel>(TotalBalanceModel())
    var balance = _balance.asStateFlow()

    private var _userUrl = MutableStateFlow<Uri>(Uri.EMPTY)
    var userUrl = _userUrl.asStateFlow()

    init {
        loadBalance()
    }

    fun loadBalance() {
        try {
            _userUrl.value = FirebaseAuth.getInstance().currentUser?.photoUrl ?: Uri.EMPTY

            viewModelScope.launch {
                linkRepo.getLinkAccount()
                balanceRepo.getTotalBalance().collect{
                    _balance.value = it
                }
            }
        } catch (e: Exception) {
            Log.e("firebaseError", "viewmodel - readData: ${e.message}")
        }
    }

    fun getAccountBalance() {
        viewModelScope.launch {
            balanceRepo.getBalanceByAccount(AccountType.CARTEIRA) {}
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