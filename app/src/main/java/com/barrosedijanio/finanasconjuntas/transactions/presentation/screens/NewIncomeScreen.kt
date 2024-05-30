package com.barrosedijanio.finanasconjuntas.transactions.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.core.components.TextFieldTransparent
import com.barrosedijanio.finanasconjuntas.core.generics.Result
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.AccountType
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.transactions.presentation.components.TextFieldDropDownAppDefault
import com.barrosedijanio.finanasconjuntas.transactions.presentation.states.ExpenseUiState
import com.barrosedijanio.finanasconjuntas.transactions.presentation.states.IncomeUiState
import com.barrosedijanio.finanasconjuntas.transactions.util.millisecondsToDateString
import com.barrosedijanio.finanasconjuntas.transactions.util.millisecondsToTimestamp
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewIncomeScreen(
    results: Result,
    uiState: IncomeUiState,
    onNavigateBackClick: () -> Unit,
    onConfirmClick: () -> Unit,
) {
    LaunchedEffect(key1 = results) {
        when (results) {
            Result.Empty -> {}
            is Result.Error -> {
                uiState.onLoadingChange(false)
                uiState.onErrorChange(results.errorMessage)
            }

            Result.Loading -> uiState.onLoadingChange(true)
            Result.OK -> {
                uiState.onLoadingChange(false)
                onNavigateBackClick()
            }
        }
    }

    val dateState = rememberDatePickerState()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    "Nova Receita",
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
                    modifier = Modifier.weight(0.3f).padding(start = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Recebido",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = openSansFontFamily
                    )
                    Checkbox(
                        checked = uiState.isReceived,
                        onCheckedChange = { uiState.onReceivedChange(it) })
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
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "date icon"
                    )
                },
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
                        Category(value, 0)
                    )
                    uiState.onCategoryDropdownExpandedChange(false)
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_category_24),
                        contentDescription = "Categoria"
                    )
                }
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
                onSelectMenuItem = { _, value ->
                    uiState.onAccountChange(
                        AccountType.valueOf(value)
                    )

                    uiState.onAccountDropdownExpandedChange(false)

                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_wallet_24),
                        contentDescription = "Conta"
                    )
                }
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
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_repeat_24),
                        contentDescription = "Repetir transação"
                    )
                },
                onValueChange = {}
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 40.dp))

        if (uiState.error.isNotEmpty()) {
            Text(
                text = uiState.error,
                color = Color.Red,
                fontSize = 16.sp,
                fontFamily = openSansFontFamily
            )
        }
        val context = LocalContext.current
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            onClick = {
                if (uiState.description == "" ||  uiState.value == "" || uiState.category.name == "") {
                    Toast.makeText(context, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }

                dateState.selectedDateMillis?.let {
                    uiState.onPaymentDateChange(millisecondsToTimestamp(it))
                }
                onConfirmClick()
            },
            shape = ShapeDefaults.Small,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF66CDAA),
            ),
            border = BorderStroke(width = 1.dp, color = Color(0xFF407A66))
        ) {

            if (uiState.loading) CircularProgressIndicator()
            else {
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

}
