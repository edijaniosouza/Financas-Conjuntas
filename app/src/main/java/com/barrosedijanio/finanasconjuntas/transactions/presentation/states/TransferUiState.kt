package com.barrosedijanio.finanasconjuntas.transactions.presentation.states

import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import java.util.Date

data class TransferUiState(
    var value: String = "",
    var onValueChange: (String) -> Unit = {},
    var description: String = "",
    var onDescriptionChange: (String) -> Unit = {},
    var transferDate: Long = Date().time,
    var onTransferDateChange: (Long) -> Unit = {},
    var account: AccountType = AccountType.CARTEIRA,
    var onAccountChange: (AccountType) -> Unit = {},
    var accountDropdownExpanded: Boolean = false,
    var onAccountDropdownExpandedChange: (Boolean) -> Unit = {},
    var accountTransfer: AccountType = AccountType.CARTEIRA,
    var onAccountTransferChange: (AccountType) -> Unit = {},
    var accountTransferDropdownExpanded: Boolean = false,
    var onAccountTransferDropdownExpandedChange: (Boolean) -> Unit = {},
    var repeat : Boolean = false,
    var onRepeatChange: (Boolean) -> Unit = {},
    val loading: Boolean = false,
    val onLoadingChange: (Boolean) -> Unit = {},
    val error: String = "",
    val onErrorChange: (String) -> Unit = {}
)