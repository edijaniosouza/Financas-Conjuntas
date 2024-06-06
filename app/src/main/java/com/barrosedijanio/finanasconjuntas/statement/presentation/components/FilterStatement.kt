package com.barrosedijanio.finanasconjuntas.statement.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FilterStatement(modifier: Modifier = Modifier, onFilterClick: (String)-> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        FilterStatementButton("Entradas", onFilterClick)
        Spacer(modifier = Modifier.padding(5.dp))
        FilterStatementButton("Saídas", onFilterClick)
        Divider(
            modifier = Modifier
                .rotate(90f)
                .width(30.dp)
        )
        FilterStatementButton("30 dias", onFilterClick)
        Spacer(modifier = Modifier.padding(5.dp))
        FilterStatementButton("60 dias", onFilterClick)
        Spacer(modifier = Modifier.padding(5.dp))
        FilterStatementButton("90 dias", onFilterClick)
    }
}

@Composable
private fun FilterStatementButton(text: String, onClick: (String) -> Unit) {

    Text(
        modifier = Modifier
            .width(57.dp)
            .background(Color.White, shape = ShapeDefaults.Medium)
            .border(BorderStroke(1.dp, Color(0xFFC2C2C2)), shape = ShapeDefaults.Medium)
            .padding(5.dp)
            .clickable {
                onClick(text)
            },
        text = text,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        textAlign = TextAlign.Center
    )
}


@Preview
@Composable
private fun FilterPreview() {
    FilterStatement(){}
}

@Preview
@Composable
private fun FilterButtonPreview() {
    FilterStatementButton("Entrada"){}
}