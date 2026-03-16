package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keypick.core.model.RecentSearch

@Entity(tableName = "recent_search")
data class RecentSearchEntity(
    @PrimaryKey
    val keyword : String,

    val timestamp : Long
)

fun RecentSearchEntity.toDomain(): RecentSearch {
    return RecentSearch(
        keyword = keyword,
        timeStamp = timestamp.toString()
    )
}