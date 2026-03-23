package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keypick.core.model.SavedKeyword

@Entity(tableName = "saved_keyword")
data class SavedKeywordEntity(

    @PrimaryKey
    val keyword : String,

    val timestamp: Long,

    val resultCount : String

)

fun SavedKeywordEntity.toDomain() : SavedKeyword{
    return SavedKeyword(
        keyword = keyword,
        timestamp = timestamp,
        resultCount = resultCount
    )
}

