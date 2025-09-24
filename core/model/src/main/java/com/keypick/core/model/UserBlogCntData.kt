package com.keypick.core.model



data class UserBlogCntData(
    val visitorcntList: List<VisitorCntData>
)

data class VisitorCntData(
    val id: String,
    val cnt: String
)