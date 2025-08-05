package com.example.keypick_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.login.LoginScreen
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen

@Composable
fun KeypickNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController=navController,
        startDestination = LoginRoute
    ){
        loginScreen()
    }
}