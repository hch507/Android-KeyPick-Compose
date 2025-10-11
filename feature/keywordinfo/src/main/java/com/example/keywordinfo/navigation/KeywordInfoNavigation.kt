package com.example.keywordinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.keywordinfo.KeywordInfoScreen
import kotlinx.serialization.Serializable

@Serializable
data class KeywordInfoRoute(val keyword : String)

fun NavController.navigateToKeywordInfo(keyword : String) = navigate(route = KeywordInfoRoute(keyword) )

fun NavGraphBuilder.keywordInfoScreen(){
    composable<KeywordInfoRoute> { backStackEntry ->
        // keyword 포함된 route 정보 가져오기
        val route = backStackEntry.toRoute<KeywordInfoRoute>()

        // 실제 화면에 keyword 전달
        KeywordInfoScreen(keyword = route.keyword)

    }
}