package com.example.nonlogin.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import com.example.nonlogin.NonLoginRoute

@Serializable
object NonLoginRoute

fun NavController.navigateToNonLogin() = navigate(route = NonLoginRoute)

fun NavGraphBuilder.nonLoginScreen(
    onMoveToLoginClick : () -> Unit
) {
    composable<NonLoginRoute> {
        NonLoginRoute(
            onMoveToLoginClick = onMoveToLoginClick
        )
    }
}