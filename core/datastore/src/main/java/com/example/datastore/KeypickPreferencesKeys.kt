package com.example.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object KeypickPreferencesKeys {
    val BLOG_ID = stringPreferencesKey("user_name")
    val STORED_KEYWORDS = stringPreferencesKey("stored_keywords")
}