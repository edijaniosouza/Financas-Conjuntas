package com.barrosedijanio.finanasconjuntas.transactions.presentation.states

import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.google.firebase.Timestamp
import java.util.Date

data class IncomeUiState(
    var value: String = "",
    var onValueChange: (String) -> Unit = {},
    var isReceived: Boolean = false,
    var onReceivedChange: (Boolean) -> Unit = {},
    var description: String = "",
    var onDescriptionChange: (String) -> Unit = {},
    var paymentDate: Timestamp = Timestamp.now(),
    var onPaymentDateChange: (Timestamp) -> Unit = {},
    var categoryDropdownExpanded: Boolean = false,
    var onCategoryDropdownExpandedChange: (Boolean) -> Unit = {},
    var category: Category = Category("", R.drawable.ic_launcher_background),
    var onCategoryChange: (Category) -> Unit = {},
    var account: AccountType = AccountType.CARTEIRA,
    var onAccountChange: (AccountType) -> Unit = {},
    var accountDropdownExpanded: Boolean = false,
    var onAccountDropdownExpandedChange: (Boolean) -> Unit = {},
    var repeat : Boolean = false,
    var onRepeatChange: (Boolean) -> Unit = {},
    val loading: Boolean = false,
    val onLoadingChange: (Boolean) -> Unit = {},
    val error: String = "",
    val onErrorChange: (String) -> Unit = {}
)