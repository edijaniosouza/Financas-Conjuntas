package com.barrosedijanio.finanasconjuntas.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.barrosedijanio.finanasconjuntas.ui.screens.LoginScreen

const val LOGIN_SCREEN = "login"
fun NavGraphBuilder.loginScreen(){
    composable(LOGIN_SCREEN){
        LoginScreen()
    }
}