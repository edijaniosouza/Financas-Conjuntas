package com.barrosedijanio.finanasconjuntas.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.barrosedijanio.finanasconjuntas.R
import com.barrosedijanio.finanasconjuntas.ui.theme.robotoFontFamily

@Composable
fun FabAppDefault(
    modifier: Modifier = Modifier,
    onCreateNewIncome: () -> Unit,
    onCreateNewExpense: () -> Unit,
    onAccountTransfer: () -> Unit
) {
    var fabOpen by remember { mutableStateOf(false) }
    val icon = if (fabOpen) Icons.Filled.Close else Icons.Filled.Create

    Column(
        modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = fabOpen) {

            Column(
                horizontalAlignment = Alignment.End,
            ) {
                FabItems(
                    "Receita",
                    itemIcon = painterResource(id = R.drawable.baseline_trending_up_24),
                    contentColor = Color(0xFF06FF4C),
                    borderColor = Color(0xFFB6FFAF),
                    onClick = {
                        onCreateNewIncome()
                    }
                )
                FabItems(
                    "Despesa",
                    itemIcon = painterResource(id = R.drawable.baseline_trending_down_24),
                    contentColor = Color(0xFFFF0000),
                    borderColor = Color(0xFFFFAFAF),
                    onClick = {
                        onCreateNewExpense()
                    }
                )
                FabItems(
                    "Transferência",
                    itemIcon = painterResource(id = R.drawable.baseline_transfer_arrows_24),
                    contentColor = Color.Black,
                    borderColor = Color.Black,
                    onClick = {
                        onAccountTransfer()
                    }
                )
            }

        }
        FloatingActionButton(
            shape = FloatingActionButtonDefaults.largeShape,
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.primary,
            onClick = {
                fabOpen = !fabOpen
            }) {
            Icon(imageVector = icon, contentDescription = null)
        }
    }

}

@Composable
private fun FabItems(
    itemName: String,
    itemIcon: Painter,
    contentColor: Color,
    borderColor: Color,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = itemName,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = robotoFontFamily
        )
        Spacer(modifier = Modifier.padding(horizontal = 5.dp))
        FloatingActionButton(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .border(1.dp, color = borderColor, shape = CircleShape),
            shape = FloatingActionButtonDefaults.largeShape,
            containerColor = Color.White,
            contentColor = contentColor,
            onClick = onClick
        ) {
            Icon(painter = itemIcon, contentDescription = null)
        }
    }
}