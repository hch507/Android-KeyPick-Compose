package com.example.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.home.HomeRoute
import com.example.home.HomeScreen
import com.example.keywordstore.navigation.keywordStoreScreen
import com.example.keywordstore.navigation.navigateTopStore
import com.example.ranking.navigation.navigateToRanking
import com.example.ranking.navigation.rankingScreen
import com.example.search.navigation.navigateToSearch
import com.example.search.navigation.searchScreen
import com.example.userbloginfo.navigation.UserBlogInfoRoute
import com.example.userbloginfo.navigation.navigateToUserBlogInfo
import com.example.userbloginfo.navigation.userBlogInfoScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
object HomeRoute

fun NavController.navigateToHome(
    navOptions: NavOptions? = null
) {
    navigate(route = HomeRoute, navOptions = navOptions)
}
fun NavGraphBuilder.homeScreen(
    onMoveToSearchClick :(String) -> Unit,
    onMoveToLogin: () -> Unit
){
    composable<HomeRoute> {
        HomeRoute(onMoveToSearchClick,onMoveToLogin)
    }
}

fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

//Todo nav 옵셥 추가해야함
fun navigateToHomeLevelDestination(
    navController: NavHostController,
    homeLevelDestination: HomeLevelDestination
) {

    when (homeLevelDestination) {
        HomeLevelDestination.USER_BLOG_INFO -> navController.navigateToUserBlogInfo()
        HomeLevelDestination.RANKING -> navController.navigateToRanking()
        HomeLevelDestination.SEARCH -> navController.navigateToSearch()
        HomeLevelDestination.STORE -> navController.navigateTopStore()
//            INTERESTS -> navController.navigateToInterests(null, topLevelNavOptions)
    }

}