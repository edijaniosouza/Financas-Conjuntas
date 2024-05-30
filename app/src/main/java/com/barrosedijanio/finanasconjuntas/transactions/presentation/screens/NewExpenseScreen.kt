package com.barrosedijanio.finanasconjuntas.transactions.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.core.components.TextFieldTransparent
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.transactions.presentation.states.ExpenseUiState
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewExpenseScreen(
    uiState: ExpenseUiState,
    onNavigateBackClick: () -> Unit,
    onConfirmClick: (Transaction) -> Unit,
) {
    val dateState = rememberDatePickerState()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Nova Despesa",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = robotoFontFamily,
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBackClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Icone Voltar")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    Modifier
                        .weight(0.7f)
                        .background(Color(0xB3FFFFFF), shape = ShapeDefaults.Small)
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        "R$",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = openSansFontFamily
                    )

                    TextField(
                        value = uiState.value,
                        onValueChange = {
                            uiState.onValueChange(it)
                        },
                        placeholder = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "0,00",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = openSansFontFamily,
                                textAlign = TextAlign.Center
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = openSansFontFamily,
                            textAlign = TextAlign.Center
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                }

                Row(
                    modifier = Modifier.weight(0.3f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Pago",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = openSansFontFamily
                    )
                    Checkbox(
                        checked = uiState.isPaid,
                        onCheckedChange = { uiState.onPaidChange(it) })
                }
            }

            TextFieldTransparent(
                value = uiState.description,
                placeholder = "Descrição",
                onValueChange = { uiState.onDescriptionChange(it) })


            var openDatePicker by rememberSaveable { mutableStateOf(false) }
            var dateChoisen by rememberSaveable { mutableStateOf("") }

            dateChoisen =
                if (dateState.selectedDateMillis != null) millisecondsToDateString(dateState.selectedDateMillis!!) else ""

            TextFieldTransparent(
                modifier = Modifier.clickable {
                    openDatePicker = !openDatePicker
                },
                value = dateChoisen,
                placeholder = "Data do pagamento",
                enable = false,
                onValueChange = {}
            )

            if (openDatePicker) {
                DatePickerDialog(
                    dismissButton = {
                        Button(
                            onClick = {
                                dateState.setSelection(null)
                                openDatePicker = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Cancelar")
                        }
                    },
                    onDismissRequest = { openDatePicker = false },
                    confirmButton = {
                        Button(onClick = { openDatePicker = false }) {
                            Text("Confirmar")
                        }
                    },
                ) {
                    DatePicker(
                        state = dateState,
                        title = {
                            Text(
                                modifier = Modifier.padding(16.dp),
                                text = "Data de pagamento"
                            )
                        },
                        showModeToggle = true,
                        dateFormatter = DatePickerFormatter(
                            selectedDateSkeleton = "dd,MM,yyyy",
                            yearSelectionSkeleton = "MM, yyyy",
                            selectedDateDescriptionSkeleton = "dd, MM, yyyy"
                        )
                    )

                }
            }
            val categories = mapOf(1 to "casa", 2 to "aluguel", 3 to "comida")

            TextFieldDropDownAppDefault(
                label = "Categoria",
                uiState.categoryDropdownExpanded,
                onExpanded = {
                    uiState.onCategoryDropdownExpandedChange(!uiState.categoryDropdownExpanded)
                },
                onDismissRequest = {
                    uiState.onCategoryDropdownExpandedChange(false)
                },
                uiState.category.name,
                categories,
                onSelectMenuItem = { _, value ->
                    uiState.onCategoryChange(
                        Category(value,0)
                    )
                    uiState.onCategoryDropdownExpandedChange(false)
                },
            )


            val accounts = AccountType.entries.associate {
                it.ordinal to it.name
            }

            TextFieldDropDownAppDefault(
                label = "Conta Debitada",
                uiState.accountDropdownExpanded,
                onExpanded = {
                    uiState.onAccountDropdownExpandedChange(!uiState.accountDropdownExpanded)
                },
                onDismissRequest = {
                    uiState.onAccountDropdownExpandedChange(false)
                },
                uiState.account.name,
                accounts,
                onSelectMenuItem = {_, value ->
                    uiState.onAccountChange(
                        AccountType.valueOf(value)
                    )

                    uiState.onAccountDropdownExpandedChange(false)

                },
            )


            TextFieldTransparent(
                modifier = Modifier.clickable {
                    uiState.onRepeatChange(!uiState.repeat)
                },
                value = "",
                placeholder = "Repetir",
                enable = false,
                trailingIcon = {
                    Switch(
                        checked = uiState.repeat,
                        onCheckedChange = { uiState.onRepeatChange(it) },
                        colors = SwitchDefaults.colors(
                            uncheckedTrackColor = Color.White
                        )
                    )
                },
                onValueChange = {}
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 40.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            onClick = {
                val timestampDate = dateState.selectedDateMillis?.let {
                    millisecondsToTimestamp(it)
                }
                onConfirmClick(
                    Transaction(
                        paid = uiState.isPaid,
                        paidDate = timestampDate,
                        value = uiState.value.toFloat(),
                        description = uiState.description,
                        category = uiState.category,
                        accountType = uiState.account,
                        isIncome = true,
                    )
                )
            },
            shape = ShapeDefaults.Small,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF66CDAA),
            ),
            border = BorderStroke(width = 1.dp, color = Color(0xFF407A66))
        ) {
            Text(
                "Adicionar",
                fontSize = 20.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TextFieldDropDownAppDefault(
    label: String,
    expanded: Boolean,
    onExpanded: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    selectedOption: String,
    list: Map<Int, String>,
    onSelectMenuItem: (Int, String) -> Unit,
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpanded,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    )
    {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(label) },
            leadingIcon = { Icon(Icons.Filled.ArrowBack, contentDescription = null) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0x80FFFFFF),
                unfocusedContainerColor = Color(0x80FFFFFF),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color(0xB5000000),
                focusedLabelColor = Color(0xB5000000),
            )
        )

        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = onDismissRequest
        ) {
            list.forEach { options ->
                DropdownMenuItem(
                    text = { Text(text = options.value) },
                    onClick = { onSelectMenuItem(options.key, options.value) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = null
                        )
                    }
                )
            }
        }
    }
}


//@SuppressLint("SimpleDateFormat")
fun millisecondsToDateString(milliseconds: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val instance = Calendar.getInstance()
    instance.timeZone = TimeZone.getTimeZone("America/Sao_Paulo")

    // Ajustado a mão pois o millisecconds recebido esta sempre com erro de 1 dia
    val oneDay = 86400000
    instance.timeInMillis = milliseconds + oneDay
    return formatter.format(instance.time)
}

fun millisecondsToTimestamp(milliseconds: Long): Timestamp {
    val oneDay = 86400000
    val ofEpochMilli = Instant.ofEpochMilli(milliseconds+oneDay)
    return Timestamp(ofEpochMilli)
}