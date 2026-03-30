package com.example.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavController.navigateToLogin(
    navOptions: NavOptions? = null
)= navigate(route = LoginRoute, navOptions = navOptions)

fun NavGraphBuilder.loginScreen(
    onNonLoginClick : () -> Unit,
    moveToMain : () -> Unit
){
    composable<LoginRoute> {
        LoginRoute(
            moveToMain= moveToMain,
            onNonLoginClick = onNonLoginClick
        )
    }
}