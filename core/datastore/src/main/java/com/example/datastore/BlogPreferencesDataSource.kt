package com.example.datastore

import javax.inject.Inject

class BlogPreferencesDataSource @Inject constructor(
    private val blogPreferencs: BlogPreferencs
) {

    suspend fun saveBlogId(blogId: String) {
        blogPreferencs.setBlogId(blogId)
    }

    suspend fun clearPreferences() {
        blogPreferencs.clear()
    }
}