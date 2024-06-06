package com.barrosedijanio.finanasconjuntas.transactions.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.data.transactions.TransactionRepositoryImpl
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.transactions.presentation.states.TransferUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransferViewModel(
    private val repository: TransactionRepositoryImpl
) : ViewModel() {

    private var _uiState = MutableStateFlow(TransferUiState())
    var uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onValueChange = { value ->
                    _uiState.update {
                        it.copy(value = value)
                    }
                },
                onDescriptionChange = { description ->
                    _uiState.update {
                        it.copy(description = description)
                    }
                },
                onTransferDateChange = { timestamp ->
                    _uiState.update {
                        it.copy(transferDate = timestamp)
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
                onAccountDropdownExpandedChange = { expanded ->
                    _uiState.update {
                        it.copy(accountDropdownExpanded = expanded)
                    }
                },
                onAccountTransferChange = { account ->
                    _uiState.update {
                        it.copy(accountTransfer = account)
                    }
                },
                onAccountTransferDropdownExpandedChange = { expanded ->
                    _uiState.update {
                        it.copy(accountTransferDropdownExpanded = expanded)
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

}