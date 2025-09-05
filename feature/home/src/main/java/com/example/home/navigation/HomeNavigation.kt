package com.example.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavController.navigateToHome() = navigate(route = HomeRoute)

fun NavGraphBuilder.homeScreen(
    onMoveToSearchClick :() -> Unit
){
    composable<HomeRoute> {
        HomeRoute(onMoveToSearchClick)
    }
}