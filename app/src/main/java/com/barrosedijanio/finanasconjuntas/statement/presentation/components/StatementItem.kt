package com.barrosedijanio.finanasconjuntas.statement.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily


@Composable
fun StatementItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row {
            Icon(
                modifier = Modifier
                    .background(Color(0xFF4FA386), shape = CircleShape)
                    .padding(5.dp)
                    .size(25.dp),
                imageVector = Icons.Default.Home,
                contentDescription = "Icone da categoria"
            )
            Column(
                Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Descrição",
                    fontSize = 10.sp,
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Categoria",
                        fontSize = 8.sp,
                        fontFamily = openSansFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .rotate(90f)
                            .width(8.dp)
                    )
                    Text(
                        text = "Carteira",
                        fontSize = 8.sp,
                        fontFamily = openSansFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                "R$ 45,00",
                fontSize = 10.sp,
                fontFamily = openSansFontFamily,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "Icone despesa paga"
            )
        }
    }
}
