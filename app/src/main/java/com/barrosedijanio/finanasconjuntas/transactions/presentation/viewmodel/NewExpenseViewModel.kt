package com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.core.generics.Result
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
                },
                onLoadingChange = { loading ->
                    _uiState.update {
                        it.copy(loading = loading)
                    }
                },
                onErrorChange = { error ->
                    _uiState.update {
                        it.copy(error = error)
                    }
                }
            )
        }
    }

    private var _result = MutableStateFlow<Result>(Result.Empty)
    val result = _result.asStateFlow()

    fun newExpense() {

        lateinit var transaction: Transaction
        try {
            transaction = Transaction(
                paid = uiState.value.isPaid,
                paidDate = uiState.value.paymentDate,
                value = uiState.value.value.toFloat(),
                description = uiState.value.description,
                category = uiState.value.category,
                accountType = uiState.value.account,
                isIncome = false,
                )
        } catch (e: Exception) {
            _result.value = Result.Error("Erro ao criar transação")
            return
        }

        viewModelScope.launch {
            repository.newTransaction(transaction).collect {
                _result.value = it
            }
        }
    }
}