package com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.transactions.presentation.states.ExpenseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewExpenseViewModel(
    private val repository: TransactionRepositoryImpl
) : ViewModel() {

    private var _uiState = MutableStateFlow(ExpenseUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onPaidChange = { newValue ->
                    _uiState.update {
                        it.copy(isPaid = newValue)
                    }
                },
                onValueChange = { value ->
                    _uiState.update {
                        it.copy(value = value)
                    }
                },
                onCategoryChange = { category ->
                    _uiState.update {
                        it.copy(category = category)
                    }
                },
                onDescriptionChange = { description ->
                    _uiState.update {
                        it.copy(description = description)
                    }
                },
                onPaymentDateChange = { date ->
                    _uiState.update {
                        it.copy(paymentDate = date)
                    }
                },
                onRepeatChange = { repeat ->
                    _uiState.update {
                        it.copy(repeat = repeat)
                    }
                },
                onAccountChange = { account ->
                    _uiState.update {
                        it.copy(account = account)
                    }
                },
                onCategoryDropdownExpandedChange = { expanded ->
                    _uiState.update {
                        it.copy(categoryDropdownExpanded = expanded)
                    }
                },
                onAccountDropdownExpandedChange = { expanded ->
                    _uiState.update {
                        it.copy(accountDropdownExpanded = expanded)
                    }
                }
            )
        }
    }

    fun addExpense(transaction: Transaction, onSuccess: () -> Unit) {

        viewModelScope.launch {
            repository.newTransaction(transaction)
        }
        onSuccess()
    }
}