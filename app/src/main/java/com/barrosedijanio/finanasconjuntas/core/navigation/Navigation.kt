package com.barrosedijanio.finanasconjuntas.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.barrosedijanio.finanasconjuntas.auth.presentation.navigation.signInScreen
import com.barrosedijanio.finanasconjuntas.auth.presentation.navigation.signUpScreen
import com.barrosedijanio.finanasconjuntas.core.components.FabAppDefault
import com.barrosedijanio.finanasconjuntas.core.components.NavigationBarDefault
import com.barrosedijanio.finanasconjuntas.home.presentation.navigation.homeScreen
import com.barrosedijanio.finanasconjuntas.transactions.navigation.newExpenseScreen
import com.barrosedijanio.finanasconjuntas.statement.presentation.navigation.statementScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val loggedUser = auth.currentUser
    val startDestination = if (loggedUser != null) Screens.Home.route else Screens.SignIn.route

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute !in listOfNonNavItems) {
                NavigationBarDefault(currentDestination, navController)
            }
        },
        floatingActionButton = {
            if (currentRoute !in listOfNonNavItems) {
                FabAppDefault(
                    onCreateNewIncome = { /*TODO*/ },
                    onCreateNewExpense = { navController.navigate(Screens.Expense.route) },
                    onAccountTransfer = {})
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = startDestination
        ) {
            signInScreen(
                goToHome = {
                    navController.navigate(Screens.Home.route)
                },
                goToCreateAccount = { navController.navigate(Screens.SignUp.route) })
            signUpScreen {
                navController.navigate(Screens.Home.route)
            }
            homeScreen()
            statementScreen()
            newExpenseScreen{
                navController.popBackStack()
            }
        }
    }

}
