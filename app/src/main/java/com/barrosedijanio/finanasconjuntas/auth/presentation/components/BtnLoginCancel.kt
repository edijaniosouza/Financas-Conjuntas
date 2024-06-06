package com.barrosedijanio.finanasconjuntas.auth.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily


@Composable
fun BtnLoginCancel(onCancel: () -> Unit) {
    TextButton(onClick = onCancel, modifier = Modifier.padding(top = 20.dp)) {
        Text(
            stringResource(R.string.cancel),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = robotoFontFamily,
            color = Color(0xFF54A88B)
        )
    }
}