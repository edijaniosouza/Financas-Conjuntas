package com.barrosedijanio.finanasconjuntas.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily
import com.barrosedijanio.finanasconjuntas.auth.domain.usercase.InputValidResult

@Composable
fun InputTextFieldDefault(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String,
    leadingIcon: ImageVector? = null,
    trailingIcon: Painter? = null,
    trailingIconClick: (() -> Unit)? = null,
    error: InputValidResult = InputValidResult.Empty,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyBoardOptions: KeyboardOptions = KeyboardOptions.Default
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
        leadingIcon = {
            if (leadingIcon != null) {
                Icon(leadingIcon, contentDescription = "Input Icon")
            }
        },
        trailingIcon = {
            if (trailingIconClick != null) {
                IconButton(onClick = trailingIconClick) {
                    if (trailingIcon != null) {
                        Icon(trailingIcon, null)
                    }
                }
            }
        },
        singleLine = true,
        label = { Text(placeHolderText) },
        visualTransformation = visualTransformation,
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
        isError = error is InputValidResult.Error,
        supportingText = {
            Column {
                if (error is InputValidResult.Error) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = error.messageId),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        keyboardOptions = keyBoardOptions,
    )
}