package com.example.network.model

import com.keypick.core.model.UserBlogCntData
import com.keypick.core.model.VisitorCntData
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


fun LoginOrCntDto.asExternalModel() : UserBlogCntData{
    return UserBlogCntData(
        visitorcntList = visitorcntList.map {
            VisitorCntData(
                id = it.id,
                cnt = it.cnt
            )
        }
    )
}