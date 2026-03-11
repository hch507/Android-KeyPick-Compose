package com.example.keypick_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.common.NavOptionProvider
import com.example.home.navigation.homeScreen


import com.example.home.navigation.navigateToHome
import com.example.keywordinfo.navigation.keywordInfoScreen
import com.example.keywordinfo.navigation.navigateToKeywordInfo

import com.example.login.navigation.LoginRoute
import com.example.login.navigation.loginScreen
import com.example.login.navigation.navigateToLogin
import com.example.nonlogin.navigation.navigateToNonLogin
import com.example.nonlogin.navigation.nonLoginScreen
import com.example.search.navigation.navigateToSearch
import com.example.search.navigation.searchScreen


@Composable
fun KeypickNavHost(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        loginScreen(
            moveToMain = {
                navController.navigateToHome(
                    navOptions = NavOptionProvider.clearBackStackAndLaunchSingle<LoginRoute>(
                        LoginRoute::class
                    )
                )
            },
            onNonLoginClick = navController::navigateToNonLogin
        )
        nonLoginScreen(
            onMoveToLoginClick = navController::navigateToLogin
        )
        homeScreen(
            onRecommendKeywordClick = { keyword ->
                navController.navigateToKeywordInfo(keyword)
            },
            onMoveToSearch = navController::navigateToSearch,
            onMoveToLogin = navController::navigateToLogin
        )
        searchScreen(
            onSearchClick = { keyword ->
                navController.navigateToKeywordInfo(keyword)
            }
        )
        keywordInfoScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )

    }
}