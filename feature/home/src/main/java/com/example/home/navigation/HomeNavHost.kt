package com.example.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.ranking.navigation.rankingScreen
import com.example.userbloginfo.navigation.UserBlogInfoRoute
import com.example.userbloginfo.navigation.userBlogInfoScreen

@Composable
fun HomeNavHost(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = UserBlogInfoRoute
    ){
        userBlogInfoScreen()
        rankingScreen()
    }
}