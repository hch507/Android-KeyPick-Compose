package com.example.search.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.search.SearchRoute
import kotlinx.serialization.Serializable

@Serializable
object SearchRoute

fun NavController.navigateToSearch() = navigate(route = SearchRoute)

fun NavGraphBuilder.searchScreen(
    onSearchClick: () -> Unit
){
    composable<SearchRoute>{
        SearchRoute(onSearchClick)
    }
}
