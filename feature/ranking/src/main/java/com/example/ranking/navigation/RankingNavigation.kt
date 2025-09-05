package com.example.ranking.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.ranking.RankingRoute
import kotlinx.serialization.Serializable

@Serializable
object RankingRoute

fun NavController.navigateToRanking() = navigate(route = RankingRoute)

fun NavGraphBuilder.rankingScreen(){
    composable<RankingRoute>{
        RankingRoute()
    }
}