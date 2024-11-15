package com.barrosedijanio.finanasconjuntas.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun BalanceCard(
    modifier: Modifier = Modifier,
    cardTitle: String,
    cardTitleSize: TextUnit,
    value: String,
    textColor: Color,
    textSize: TextUnit,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                cardTitle,
                color = textColor,
                fontSize = cardTitleSize,
                fontWeight = FontWeight.SemiBold,
                fontFamily = robotoFontFamily
            )
            Text(
                value,
                color = textColor,
                fontSize = textSize,
                fontWeight = FontWeight.Bold,
                fontFamily = openSansFontFamily
            )
        }
    }
}
