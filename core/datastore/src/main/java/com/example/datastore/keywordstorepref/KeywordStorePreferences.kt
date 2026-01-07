package com.example.datastore.keywordstorepref

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastore.KeypickPreferencesKeys.STORED_KEYWORDS
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DATASTORE_KEYWORD_STORE_NAME = "keyword_store_preferences"

private val Context.keywordDataStore by preferencesDataStore(name = DATASTORE_KEYWORD_STORE_NAME)

class KeywordStorePreferences @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun saveKeywords( keywords: String) {
        context.keywordDataStore.edit { prefs ->
            prefs[STORED_KEYWORDS] = keywords
        }
    }

    // 읽기
    suspend fun getKeywordsOnce(): String{
        return context.keywordDataStore.data
            .map { prefs -> prefs[STORED_KEYWORDS] ?: "" } // 그냥 String 반환
            .first()
    }
}