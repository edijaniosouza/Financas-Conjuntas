package com.barrosedijanio.finanasconjuntas.core.navigation

sealed class Screens(val route: String) {
    data object SignIn: Screens("login")
    data object SignUp: Screens("register")
    data object Home: Screens("home")
    data object Statement: Screens("statement")
    data object Expense: Screens("expense")
    data object Income: Screens("income")
}

