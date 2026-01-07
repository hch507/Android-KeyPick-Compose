package com.example.datastore.keywordstorepref

import android.util.Log
import javax.inject.Inject
import kotlin.math.log


class KeywordStorePreferencesDataSource @Inject constructor(
    private val keywordStorePreferences: KeywordStorePreferences
){
    private val DELIMITER = "||"
    suspend fun getKeywords(): List<String> {
        val raw = keywordStorePreferences.getKeywordsOnce()
        return raw.split(DELIMITER).filter { it.isNotBlank() }
    }

    // 키워드 추가
    suspend fun addKeyword(keyword: String) {
        val current = getKeywords().toMutableList()
        for (i in current){
            Log.d("debug_save_keyword", "addKeyword: $i")
        }
        if (!current.contains(keyword)) {  // 중복 방지
            current.add(keyword)
        }
        keywordStorePreferences.saveKeywords(current.joinToString(DELIMITER))
    }

    // 키워드 삭제
    suspend fun deleteKeyword(keyword: String) {
        val current = getKeywords().toMutableList()
        if (current.contains(keyword)) {
            current.remove(keyword)
        }
        keywordStorePreferences.saveKeywords(current.joinToString(DELIMITER))
    }
}