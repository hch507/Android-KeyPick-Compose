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

){
    composable<LoginRoute> {
        LoginRoute()
    }
}