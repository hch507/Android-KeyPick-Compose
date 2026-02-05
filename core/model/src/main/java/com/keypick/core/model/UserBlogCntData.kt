package com.keypick.core.model



data class UserBlogCntData(
    val blogId : String,
    val visitorCntList: List<VisitorCntData>
)

data class VisitorCntData(
    val id: String,
    val cnt: String
)