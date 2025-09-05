package com.example.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.home.navigation.HomeLevelDestination
import com.example.home.navigation.HomeNavHost
import com.example.ranking.navigation.navigateToRanking
import com.example.userbloginfo.navigation.navigateToUserBlogInfo
import kotlin.reflect.KClass
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

@Composable
internal fun HomeRoute(
    onSearchClick :() -> Unit
) {
    val navController = rememberNavController()
    HomeScreen(navController, onSearchClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onSearchClick :() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main") },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    Button(
                        onClick = onSearchClick
                    ) {
                        Text(text = "search")
                    }
                }
            )
        },
        bottomBar = {BottomNavigationBar(navController)}
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HomeNavHost(navController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    destinations: List<HomeLevelDestination> = HomeLevelDestination.entries
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestinationRoute = currentBackStack?.destination

    NavigationBar {
        destinations.forEach { destination ->

            val isSelected = currentDestinationRoute
                .isRouteInHierarchy(destination.baseRoute)

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navigateToHomeLevelDestination(navController, destination)
                },
                icon = {
                    Box(modifier = Modifier.size(0.dp))
                },
                label = {
                    Text(text = stringResource(destination.titleText))
                },
                alwaysShowLabel = true
            )
        }
    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
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