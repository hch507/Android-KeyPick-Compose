package com.example.keywordinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.keywordinfo.KeywordInfoRoute
import kotlinx.serialization.Serializable

@Serializable
object KeywordInfoRoute

fun NavController.navigateToKeywordInfo() = navigate(route =KeywordInfoRoute )

fun NavGraphBuilder.keywordInfoScreen(){
    composable<KeywordInfoRoute> {
        KeywordInfoRoute()
    }
}