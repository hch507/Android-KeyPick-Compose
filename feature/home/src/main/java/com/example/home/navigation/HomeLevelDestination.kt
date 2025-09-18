package com.example.home.navigation

import androidx.annotation.StringRes
import com.example.home.R
import com.example.ranking.navigation.RankingRoute
import com.example.search.navigation.SearchRoute
import com.example.userbloginfo.navigation.UserBlogInfoRoute
import kotlin.reflect.KClass

enum class HomeLevelDestination(
    @StringRes val titleText: Int,
    val route: KClass<*>,
) {
    USER_BLOG_INFO(
        titleText = R.string.home_title,
        route = UserBlogInfoRoute::class,
    ),
    RANKING(
        titleText = R.string.rank_title,
        route = RankingRoute::class,
    ),
    SEARCH(
        titleText = R.string.search_title,
        route = SearchRoute::class,
    ),
    STORE(
        titleText = R.string.store_title,
        route = RankingRoute::class,
    )

}