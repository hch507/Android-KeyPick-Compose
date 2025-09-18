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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.home.navigation.HomeLevelDestination
import com.example.home.navigation.HomeNavHost
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import com.example.home.navigation.isRouteInHierarchy
import com.example.home.navigation.navigateToHomeLevelDestination

@Composable
internal fun HomeRoute(
    onMoveToSearchClick :() -> Unit
) {
    val navController = rememberNavController()
    HomeScreen(navController, onMoveToSearchClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onMoveToSearchClick :() -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main") },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    Button(
                        onClick = onMoveToSearchClick
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
            HomeNavHost(navController,onMoveToSearchClick)
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
                .isRouteInHierarchy(destination.route)

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

