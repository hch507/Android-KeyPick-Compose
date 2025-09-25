package com.keypick.core.model



data class UserBlogCntData(
    val blogId : String,
    val visitorcntList: List<VisitorCntData>
)

data class VisitorCntData(
    val id: String,
    val cnt: String
)