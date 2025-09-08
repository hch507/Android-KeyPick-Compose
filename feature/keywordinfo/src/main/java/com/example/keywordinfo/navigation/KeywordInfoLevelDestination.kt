package com.example.keywordinfo.navigation

import androidx.annotation.StringRes
import com.example.keywordinfo.R

enum class KeywordInfoLevelDestination(
    @StringRes val titleText: Int,
) {
    KEYWORD_DETAIL(
        titleText = R.string.keyword_detail_title
    ),
    RELETED_KEYWORDS(
        titleText = R.string.releted_keywords_title
    )
}