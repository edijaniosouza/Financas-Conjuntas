package com.barrosedijanio.finanasconjuntas.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationSetup() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = LOGIN_SCREEN) {
        loginScreen()
    }
}