package com.example.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BlogPreferencesDataSource @Inject constructor(
    private val blogPreferencs: BlogPreferencs
) {

    suspend fun getBlogId() : String{
        return blogPreferencs.getBlogId()
    }
    suspend fun saveBlogId(blogId: String) {
        blogPreferencs.setBlogId(blogId)
    }

    suspend fun clearPreferences() {
        blogPreferencs.clear()
    }
}