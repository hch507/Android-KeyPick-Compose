package com.example.keypick_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.login.LoginScreen
import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLogin
import com.example.nonlogin.navigation.navigateToNonLogin
import com.example.nonlogin.navigation.nonLoginScreen

@Composable
fun KeypickNavHost(
    navController: NavHostController
) {

    NavHost(
        navController=navController,
        startDestination = LoginRoute
    ){
        loginScreen(
            onLoginClick = {},
            onNonLoginClick = navController::navigateToNonLogin
        )
        nonLoginScreen(
            onMoveToLoginClick = navController::navigateToLogin
        )
    }
}