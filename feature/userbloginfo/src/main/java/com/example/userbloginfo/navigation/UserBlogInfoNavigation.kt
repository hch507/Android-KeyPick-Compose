package com.example.userbloginfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.userbloginfo.UserBlogInfoRoute
import kotlinx.serialization.Serializable

@Serializable
object UserBlogInfoRoute

fun NavController.navigateToUserBlogInfo() = navigate(route = UserBlogInfoRoute)

fun NavGraphBuilder.userBlogInfoScreen(
    onMoveToLogin : () -> Unit,
    onRecommendKeywordSearch : (String)-> Unit
){
    composable<UserBlogInfoRoute> {
        UserBlogInfoRoute(onMoveToLogin = onMoveToLogin, onRecommendKeywordSearch = onRecommendKeywordSearch)
    }
}