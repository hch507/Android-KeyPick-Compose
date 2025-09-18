package com.example.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.home.HomeRoute
import com.example.ranking.navigation.navigateToRanking
import com.example.userbloginfo.navigation.navigateToUserBlogInfo
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

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

fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false

//Todo nav 옵셥 추가해야함
fun navigateToHomeLevelDestination(
    navController: NavHostController,
    homeLevelDestination: HomeLevelDestination
) {
//        val topLevelNavOptions = navOptions {
//            // Pop up to the start destination of the graph to
//            // avoid building up a large stack of destinations
//            // on the back stack as users select items
//            popUpTo(navController.graph.findStartDestination().id) {
//                saveState = true
//            }
//            // Avoid multiple copies of the same destination when
//            // reselecting the same item
//            launchSingleTop = true
//            // Restore state when reselecting a previously selected item
//            restoreState = true
//        }

    when (homeLevelDestination) {
        HomeLevelDestination.USER_BLOG_INFO -> navController.navigateToUserBlogInfo()
        HomeLevelDestination.RANKING -> navController.navigateToRanking()
//            INTERESTS -> navController.navigateToInterests(null, topLevelNavOptions)
    }

}