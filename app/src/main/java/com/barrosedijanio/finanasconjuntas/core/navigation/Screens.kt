package com.barrosedijanio.finanasconjuntas.core.navigation

sealed class Screens(val route: String) {
    data object SignIn: Screens("login")
    data object SignUp: Screens("register")
    data object ForgotPassword: Screens("forgotPassword")
    data object VerficationCode: Screens("verificationCode")
    data object NewPassword: Screens("newPassword")
    data object Profile: Screens("profile")
    data object Home: Screens("home")
    data object Statement: Screens("statement")
    data object Expense: Screens("expense")
    data object Income: Screens("income")
    data object Transfer: Screens("transfer")
    data object Wallet: Screens("wallet")
}

