package com.example.network.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "visitorcnts")
data class LoginOrCntDto(
    @Element(name = "visitorcnt")
    val visitorcntList: List<VisitorCnt>
)

@Xml(name = "visitorcnt")
data class VisitorCnt(
    @Attribute(name = "id")
    val id: String,

    @Attribute(name = "cnt")
    val cnt: String
)