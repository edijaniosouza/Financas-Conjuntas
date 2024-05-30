package com.barrosedijanio.finanasconjuntas.transactions.presentation.states

import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import java.util.Date

data class ExpenseUiState(
    var value: String = "",
    var onValueChange: (String) -> Unit = {},
    var isPaid: Boolean = false,
    var onPaidChange: (Boolean) -> Unit = {},
    var description: String = "",
    var onDescriptionChange: (String) -> Unit = {},
    var paymentDate: Date = Date(),
    var onPaymentDateChange: (Date) -> Unit = {},
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
    val onAdd: () -> Unit = {},
)