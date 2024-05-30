package com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.transactions.presentation.states.ExpenseUiState
import com.barrosedijanio.finanasconjuntas.transactions.presentation.states.IncomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewIncomeViewModel(
    private val repository: TransactionRepositoryImpl
) : ViewModel() {

    private var _uiState = MutableStateFlow(IncomeUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onReceivedChange = { newValue ->
                    _uiState.update {
                        it.copy(isReceived = newValue)
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
                onPaymentDateChange = { timestamp ->
                    _uiState.update {
                        it.copy(paymentDate = timestamp)
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

    fun addIncome() {

        lateinit var transaction: Transaction
        try {
            transaction = Transaction(
                paid = uiState.value.isReceived,
                paidDate = uiState.value.paymentDate,
                value = uiState.value.value.toFloat(),
                description = uiState.value.description,
                category = uiState.value.category,
                accountType = uiState.value.account,
                isIncome = true,
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