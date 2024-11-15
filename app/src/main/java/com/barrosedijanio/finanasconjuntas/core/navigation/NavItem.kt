package com.barrosedijanio.finanasconjuntas.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.barrosedijanio.finanasconjuntas.R

data class NavItem(
    val label: String,
    val icon: @Composable() (() -> Unit),
    val route: String
)

val listOfNavItems = listOf<NavItem>(
    NavItem(
        label = "Home",
        icon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = null
            )
        },
        route = Screens.Home.route
    ),
    NavItem(
        label = "Transações",
        icon = {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.List,
                contentDescription = null
            )
        },
        route = Screens.Statement.route
    ),
    NavItem(
        label = "Relatórios",
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_chart),
                contentDescription = null
            )
        },
        route = Screens.Statement.route
    ),
    NavItem(
        label = "Carteira",
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_wallet_24),
                contentDescription = null
            )
        },
        route = Screens.Wallet.route
    ),
    NavItem(
        label = "Perfil",
        icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        },
        route = Screens.Profile.route
    ),
)

val listOfNonNavItems =
    listOf(
        Screens.SignIn.route,
        Screens.SignUp.route,
        Screens.NewPassword.route,
        Screens.VerficationCode.route,
        Screens.ForgotPassword.route,
        Screens.Expense.route,
        Screens.Income.route,
        Screens.Profile.route,
        Screens.Transfer.route
    )

@Composable
fun tt(modifier: Modifier = Modifier) {
    Icon(painter = painterResource(id = R.drawable.ic_chart), contentDescription = null)
}