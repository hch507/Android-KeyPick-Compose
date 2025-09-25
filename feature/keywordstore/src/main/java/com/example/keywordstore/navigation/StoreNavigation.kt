package com.example.keywordstore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.keywordstore.KeywordStoreRoute
import kotlinx.serialization.Serializable

@Serializable
object KeywordStoreRoute

fun NavController.navigateTopStore() = navigate(route = KeywordStoreRoute)

fun NavGraphBuilder.keywordStoreScreen(

){
    composable<KeywordStoreRoute> {
        KeywordStoreRoute()
    }
}