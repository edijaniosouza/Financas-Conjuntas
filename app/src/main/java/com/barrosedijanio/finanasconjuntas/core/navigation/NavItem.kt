package com.barrosedijanio.finanasconjuntas.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
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
)

val listOfNonNavItems = listOf(Screens.SignIn.route, Screens.SignUp.route, Screens.Expense.route, Screens.Income.route)