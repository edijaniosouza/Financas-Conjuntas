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

    fun getTransactions() {
        viewModelScope.launch {
            repository.allTransactions().collectLatest {
                _transactions.value = it
            }
        }
    }

    fun getTransactionByFilter(filter: String) {

        viewModelScope.launch {
            when (filter) {
                "Entradas" -> {
                    repository.getTransactionByType(true).collectLatest {
                        _transactions.value = it
                    }
                }
                "Saídas" -> {
                    repository.getTransactionByType(false).collectLatest {
                        _transactions.value = it
                    }
                }
                "30 dias" ->{
                    repository.getTransactionByPeriod(30).collectLatest {
                        _transactions.value = it
                    }
                }
                "60 dias" -> {
                    repository.getTransactionByPeriod(60).collectLatest {
                        _transactions.value = it
                    }
                }
                "90 dias" -> {
                    repository.getTransactionByPeriod(90).collectLatest {
                        _transactions.value = it
                    }
                }
                else -> {}
            }
        }
    }
}