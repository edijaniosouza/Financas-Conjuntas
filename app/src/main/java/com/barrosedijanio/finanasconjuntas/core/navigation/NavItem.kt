package com.barrosedijanio.finanasconjuntas.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems = listOf<NavItem>(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.Home.route
    ),
    NavItem(
        label = "Transactions",
        icon = Icons.Default.List,
        route = Screens.Statement.route
    ),
    NavItem(
        label = "Relatórios",
        icon = Icons.Default.Star,
        route = Screens.Statement.route
    ),
    NavItem(
        label = "Carteira",
        icon = Icons.Default.Warning,
        route = Screens.Wallet.route
    ),
)

val listOfNonNavItems =
    listOf(
        Screens.SignIn.route,
        Screens.SignUp.route,
        Screens.Expense.route,
        Screens.Income.route,
        Screens.Profile.route,
        Screens.Transfer.route
    )