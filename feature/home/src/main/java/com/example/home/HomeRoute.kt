package com.example.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.designsystem.R
import com.example.home.navigation.isRouteInHierarchy
import com.example.home.navigation.navigateToHomeLevelDestination

@Composable
internal fun HomeRoute(
    onMoveToSearchClick: () -> Unit
) {
    val navController = rememberNavController()

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    val selectedTab = remember(currentDestination) {
        HomeLevelDestination.entries.find {
            currentDestination.isRouteInHierarchy(it.route)
        } ?: HomeLevelDestination.USER_BLOG_INFO
    }
    HomeScreen(navController, onMoveToSearchClick, selectedTab)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onMoveToSearchClick: () -> Unit,
    selectTab: HomeLevelDestination
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(selectTab.titleText)) },
                colors = TopAppBarDefaults.topAppBarColors(),
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search_nav),
                        tint = null,
                        contentDescription = stringResource(com.example.home.R.string.search_title),
                        modifier = Modifier.padding(horizontal = 30.dp)
                    )
                })
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectTab = selectTab
            )
        }) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HomeNavHost(navController, onMoveToSearchClick)
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    destinations: List<HomeLevelDestination> = HomeLevelDestination.entries,
    selectTab: HomeLevelDestination
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.Transparent)
            .border(
                0.1.dp,
                MaterialTheme.colorScheme.tertiaryContainer,
                RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
    ) {
        NavigationBar {
            destinations.forEach { destination ->

                val isSelected = destination == selectTab

                NavigationBarItem(
                    selected = isSelected, onClick = {
                        navigateToHomeLevelDestination(navController, destination)
                    }, icon = {
                        Icon(
                            painter = painterResource(id = destination.iconRes),
                            contentDescription = stringResource(id = destination.titleText),
                            tint = if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }, label = {
                        Text(text = stringResource(destination.titleText))
                    }, alwaysShowLabel = true
                )
            }
        }
    }

}

