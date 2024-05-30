package com.barrosedijanio.finanasconjuntas.statement.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.firebase.data.balance.FIREBASEDATA
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StatementViewModel(
    private val repository: TransactionRepositoryImpl
) : ViewModel() {

    private var _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    var transactions = _transactions.asStateFlow()

    init {
        getTransactions()
    }
    private fun getTransactions() {
        viewModelScope.launch {
            Log.i(FIREBASEDATA, "getTransactions: fora do alltransactions")

            repository.allTransactions().collectLatest{
                _transactions.value = it
                Log.i(FIREBASEDATA, "getTransactions: $it")
            }
        }
    }
}