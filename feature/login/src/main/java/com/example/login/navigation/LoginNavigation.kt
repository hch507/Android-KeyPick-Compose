package com.example.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
object LoginRoute

fun NavController.navigateToLogin()= navigate(route = LoginRoute)

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