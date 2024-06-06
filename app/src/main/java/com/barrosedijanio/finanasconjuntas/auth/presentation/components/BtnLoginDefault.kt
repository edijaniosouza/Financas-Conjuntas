package com.barrosedijanio.finanasconjuntas.auth.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.barrosedijanio.finanasconjuntas.R


@Composable
fun BtnLoginDefault(
    enable: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        Modifier
            .fillMaxWidth(),
        shape = ShapeDefaults.Medium,
        border = if (enable) BorderStroke(
            width = 1.dp,
            color = Color(0xFF407A66)
        ) else null,
        enabled = enable
    ) {
        Text(text)
    }
}