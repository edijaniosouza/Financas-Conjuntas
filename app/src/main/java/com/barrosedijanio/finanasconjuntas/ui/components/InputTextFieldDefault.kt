package com.barrosedijanio.finanasconjuntas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily
import com.barrosedijanio.finanasconjuntas.util.validators.LoginInputValid

@Composable
fun InputTextFieldDefault(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    error: LoginInputValid = LoginInputValid.Empty,
) {
    OutlinedTextField(
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            focusedIndicatorColor = Color(0x80000000),
            unfocusedIndicatorColor = Color(0x80000000),
        ),
        placeholder = {
            Text(
                text = placeHolderText,
                fontSize = 15.sp,
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0x4D000000)
            )
        },
        shape = ShapeDefaults.Medium,
        isError = error is LoginInputValid.Error,
        supportingText = {
            Column {
                if (error is LoginInputValid.Error) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = error.messageId),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    )
}