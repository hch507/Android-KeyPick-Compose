package com.example.home.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.home.R
import com.example.keywordstore.navigation.KeywordStoreRoute
import com.example.ranking.navigation.RankingRoute
import com.example.search.navigation.SearchRoute
import com.example.userbloginfo.navigation.UserBlogInfoRoute
import kotlin.reflect.KClass

enum class HomeLevelDestination(
    @StringRes val titleText: Int,
    val route: KClass<*>,
    @DrawableRes val iconRes : Int
) {
    USER_BLOG_INFO(
        titleText = R.string.home_title,
        route = UserBlogInfoRoute::class,
        iconRes = com.example.designsystem.R.drawable.ic_home_nav
    ),
    RANKING(
        titleText = R.string.rank_title,
        route = RankingRoute::class,
        iconRes = com.example.designsystem.R.drawable.ic_rank_nav
    ),
    STORE(
        titleText = R.string.store_title,
        route = KeywordStoreRoute::class,
        iconRes = com.example.designsystem.R.drawable.ic_store_nav
    )

}