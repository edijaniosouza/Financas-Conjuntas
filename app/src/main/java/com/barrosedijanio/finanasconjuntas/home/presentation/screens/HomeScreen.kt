package com.barrosedijanio.finanasconjuntas.home.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.core.components.TopBarAppDefault
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Category
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.home.data.TotalBalanceModel
import com.barrosedijanio.finanasconjuntas.home.presentation.components.BalanceCard
import com.barrosedijanio.finanasconjuntas.statement.presentation.components.StatementItem
import com.barrosedijanio.finanasconjuntas.transactions.util.convertToBrazilianCurrency
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily

@Composable
fun HomeScreen(
    balance: TotalBalanceModel,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBarAppDefault(
                onProfileClick = onProfileClick,
                onNotificationClick = onNotificationClick
            )

            val balanceColor = if(balance.totalBalance < 0f) Color.Red else Color(0xFF4A3B2C)
            BalanceCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(10.dp),
                cardTitle = stringResource(R.string.balance),
                value = convertToBrazilianCurrency(balance.totalBalance),
                cardTitleSize = 18.sp,
                textColor = balanceColor,
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
                    cardTitle = stringResource(R.string.shared),
                    value = convertToBrazilianCurrency(balance.totalShared),
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
                        cardTitle = stringResource(R.string.incomes),
                        value = convertToBrazilianCurrency(balance.totalIncome),
                        cardTitleSize = 14.sp,
                        textColor = Color(0xFF599358),
                        textSize = 24.sp,
                        onClick = {}
                    )
                    BalanceCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        cardTitle = stringResource(R.string.expenses),
                        value = convertToBrazilianCurrency(balance.totalExpense),
                        cardTitleSize = 14.sp,
                        textColor = Color(0xFF692828),
                        textSize = 24.sp,
                        onClick = {}
                    )
                }
            }
        }

        ElevatedCard(
            modifier = Modifier.padding(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = { /*TODO*/ })
        {
            Text(
                text = "ORÇAMENTO MENSAL",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF57A389),
                modifier = Modifier.padding(10.dp)
            )
            HorizontalDivider(Modifier.padding(horizontal = 10.dp))

            val pieChartData = PieChartData(
                slices = listOf(
                    PieChartData.Slice("SciFi", 65f, Color(0xFF333333)),
                    PieChartData.Slice("Comedy", 35f, Color(0xFF666a86)),
                    PieChartData.Slice("Drama", 10f, Color(0xFF95B8D1)),
                    PieChartData.Slice("Romance", 40f, Color(0xFFF53844))
                ),
                plotType = PlotType.Pie
            )

            val pieChartConfig = PieChartConfig(
                labelVisible = true,
                isAnimationEnable = true,
                showSliceLabels = true,
                animationDuration = 1500
            )

            PieChart(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally),
                pieChartData = pieChartData,
                pieChartConfig = pieChartConfig
            )
        }

        ElevatedCard(
            modifier = Modifier
                .padding(15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "ÚLTIMAS DESPESAS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF57A389),
                modifier = Modifier.padding(10.dp)
            )
            HorizontalDivider(Modifier.padding(horizontal = 10.dp))

            val list = listOf(
                Transaction(
                    description = "Teste 1",
                    category = Category("Mercado", 0),
                    value = 1f
                ),
                Transaction(
                    description = "Teste 2",
                    category = Category("Mercado", 0),
                    value = 1f
                ),
                Transaction(
                    description = "Teste 3",
                    category = Category("Mercado", 0),
                    value = 1f
                ),
                Transaction(
                    description = "Teste 4",
                    category = Category("Mercado", 0),
                    value = 1f
                ),
                Transaction(
                    description = "Teste 5",
                    category = Category("Mercado", 0),
                    value = 1f
                ),
                Transaction(
                    description = "Teste 6",
                    category = Category("Mercado", 0),
                    value = 1f
                ),
                Transaction(
                    description = "Teste 7",
                    category = Category("Mercado", 0),
                    value = 1f
                )
            )

            for(index in 0..4){
                StatementItem(list[index])
            }

            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Mais detalhes",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = openSansFontFamily,
                color = Color.Black.copy(alpha = 0.5f),
                textDecoration = TextDecoration.Underline
            )
        }
    }

}


//@Preview(showBackground = true)
//@Composable
//private fun HomeScreenPreview() {
//    HomeScreen(TotalBalanceModel())
//}