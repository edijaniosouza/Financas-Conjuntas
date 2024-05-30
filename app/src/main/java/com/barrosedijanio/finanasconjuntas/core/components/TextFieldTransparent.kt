package com.barrosedijanio.finanasconjuntas.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.barrosedijanio.finanasconjuntas.R

@Composable
fun TextFieldTransparent(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    leadingIcon: @Composable() (() -> Unit)? = { Icon(
        painter = painterResource(id = R.drawable.baseline_short_text_24),
        contentDescription = null
    )},
    //Painter = painterResource(id = R.drawable.baseline_short_text_24),
    trailingIcon: @Composable() (() -> Unit)? = null,
    enable: Boolean = true,
    onValueChange: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            enabled = enable,
            label = { Text(placeholder) },
            leadingIcon = leadingIcon,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Black,
                disabledPlaceholderColor = Color.Black,
                disabledLabelColor = Color(0xB5000000),
                disabledLeadingIconColor = Color(0xFF000000),
            ),
            singleLine = true,
            trailingIcon = trailingIcon
        )

        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldTransparentPreview() {
    TextFieldTransparent(value = "", placeholder = "Descrição", onValueChange = {})
}