package com.example.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DATASTORE_NAME = "blog_preferences"

private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class BlogPreferencs @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun getBlogId() {
        context.dataStore.data
            .map { preferences -> preferences[BlogPreferencesKeys.BLOG_ID] }
    }

    suspend fun setBlogId(blogId: String) {
        context.dataStore.edit { preferences ->
            preferences[BlogPreferencesKeys.BLOG_ID] = blogId
        }
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}