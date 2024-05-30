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
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily


@Composable
fun StatementItem(
    transaction: Transaction
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .background(Color(0xFF4FA386), shape = CircleShape)
                    .padding(5.dp)
                    .size(25.dp),
                painter = painterResource(id = R.drawable.baseline_category_24),
                contentDescription = "Categoria"
            )
            Column(
                Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    transaction.description,
                    fontSize = 14.sp,
                    fontFamily = openSansFontFamily,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = transaction.category.name,
                        fontSize = 12.sp,
                        fontFamily = openSansFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Divider(
                        modifier = Modifier
                            .rotate(90f)
                            .width(8.dp)
                    )
                    Text(
                        text = transaction.accountType.name,
                        fontSize = 12.sp,
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
                "R$ ${transaction.value}",
                fontSize = 14.sp,
                fontFamily = openSansFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = if (transaction.isIncome) Color(0xFF12A818) else Color(0xFFA81212)
            )

            Icon(
                imageVector = if (transaction.paid) Icons.Filled.CheckCircle else Icons.Filled.Check,
                contentDescription = "Icone despesa paga"
            )
        }
    }
}

@Preview
@Composable
private fun StatementItemPreview() {
    StatementItem(Transaction(description = "", value = 1f, category = Category("", 0)))
}