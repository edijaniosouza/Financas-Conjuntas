package com.barrosedijanio.finanasconjuntas.statement.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.core.components.TopBarAppDefault
import com.barrosedijanio.finanasconjuntas.firebase.domain.model.Transaction
import com.barrosedijanio.finanasconjuntas.statement.presentation.components.FilterStatement
import com.barrosedijanio.finanasconjuntas.statement.presentation.components.StatementItem
import com.barrosedijanio.finanasconjuntas.statement.presentation.uistate.StatementUiState
import com.barrosedijanio.finanasconjuntas.transactions.util.millisecondsToDateString
import com.barrosedijanio.finanasconjuntas.ui.theme.openSansFontFamily
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily

@Composable
fun StatementScreen(
    transactions: List<Transaction>,
    onFilterClick: (String) -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarAppDefault({},{})

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            text = stringResource(R.string.lasts_transactions),
            color = Color(0xFF4FA386),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        HorizontalDivider(Modifier.padding(15.dp))
        FilterStatement(onFilterClick = onFilterClick)

        if (transactions.isEmpty()) {
            Column (
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.no_transactions),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = robotoFontFamily
                )
            }
        }

        val listOfDate = mutableListOf<Long>()
        LazyColumn {
            items(transactions) { transaction ->
                if(transaction.paidDate !in listOfDate){
                    listOfDate.add(transaction.paidDate)

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        text = millisecondsToDateString(transaction.paidDate),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = openSansFontFamily
                    )
                }

                StatementItem(transaction)
            }
        }
    }
}


//@Preview(showSystemUi = true)
//@Composable
//private fun StatementScreenPreview() {
//    StatementScreen(uiState = ,emptyList()){}
//}