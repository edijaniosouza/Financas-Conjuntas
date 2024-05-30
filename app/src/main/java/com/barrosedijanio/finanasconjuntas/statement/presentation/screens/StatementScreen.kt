package com.barrosedijanio.finanasconjuntas.statement.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.core.components.TopBarAppDefault
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.statement.presentation.components.FilterStatement
import com.barrosedijanio.finanasconjuntas.statement.presentation.components.StatementItem
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily

@Composable
fun StatementScreen(
    transactions: List<Transaction>
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarAppDefault()

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            text = "Últimas movimentações",
            color = Color(0xFF4FA386),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Divider(Modifier.padding(15.dp))
        FilterStatement()

        if (transactions.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                text = "Não há movimentações",
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            text = "15/05/2024",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = openSansFontFamily
        )

        LazyColumn {
            items(transactions) { transaction ->
                StatementItem(transaction)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun StatementScreenPreview() {
    StatementScreen(emptyList())
}