package com.example.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.home.navigation.HomeNavHost

@Composable
internal fun HomeRoute(

) {
    val navController = rememberNavController()
    HomeScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Main") },

                )
        },
        bottomBar = { /* BottomNavigation */ }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            HomeNavHost(navHostController)
        }
    }
}