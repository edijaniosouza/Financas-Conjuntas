package com.barrosedijanio.finanasconjuntas.transactions.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TextFieldDropDownAppDefault(
    label: String,
    expanded: Boolean,
    onExpanded: (Boolean) -> Unit,
    onDismissRequest: () -> Unit,
    selectedOption: String,
    list: Map<Int, String>,
    leadingIcon: @Composable (() -> Unit),
    onSelectMenuItem: (Int, String) -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = onExpanded,
            modifier = Modifier
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
                leadingIcon = leadingIcon,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color(0x80FFFFFF),
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
//                unfocusedContainerColor = Color(0x80FFFFFF),
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

        Divider()
    }

}

