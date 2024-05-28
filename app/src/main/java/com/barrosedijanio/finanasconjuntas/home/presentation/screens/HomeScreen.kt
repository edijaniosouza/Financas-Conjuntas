package com.barrosedijanio.finanasconjuntas.home.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.core.components.FabAppDefault
import com.barrosedijanio.finanasconjuntas.core.components.TopBarAppDefault
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.home.presentation.components.BalanceCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    list: List<Transaction> = emptyList(),
    onLoad: () -> Unit,
) {
    LaunchedEffect(Unit) {
        onLoad()
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBarAppDefault()

            BalanceCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(10.dp),
                cardTitle = "Saldo",
                value = "R$ 3.500,00",
                cardTitleSize = 18.sp,
                textColor = Color(0xFF4A3B2C),
                textSize = 32.sp,
                onClick = {}
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BalanceCard(
                    modifier = Modifier
                        .height(180.dp)
                        .weight(0.5f)
                        .padding(start = 5.dp),
                    cardTitle = "COMPARTILHADO",
                    value = "R$ 1.205,50",
                    cardTitleSize = 14.sp,
                    textColor = Color(0xFF39646D),
                    textSize = 24.sp,
                    onClick = {}
                )

                Column(
                    Modifier
                        .weight(0.5f)
                        .padding(5.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    BalanceCard(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .weight(0.5f),
                        cardTitle = "RECEITAS",
                        value = "R$ 3.500,00",
                        cardTitleSize = 14.sp,
                        textColor = Color(0xFF599358),
                        textSize = 24.sp,
                        onClick = {}
                    )
                    BalanceCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        cardTitle = "DESPESAS",
                        value = "R$ 3.500,00",
                        cardTitleSize = 14.sp,
                        textColor = Color(0xFF692828),
                        textSize = 24.sp,
                        onClick = {}
                    )
                }
            }

        }

//        FabAppDefault(
//            modifier = Modifier.align(Alignment.End),
//            {}, {}, {}
//        )
    }

}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(listOf(), {})
}