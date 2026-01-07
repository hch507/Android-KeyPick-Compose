package com.example.datastore.blogprerf

import javax.inject.Inject

class BlogPreferencesDataSource @Inject constructor(
    private val blogPreferences: BlogPreferences
) {

    suspend fun getBlogId() : String{
        return blogPreferences.getBlogId()
    }
    suspend fun saveBlogId(blogId: String) {
        blogPreferences.setBlogId(blogId)
    }

    suspend fun clearPreferences() {
        blogPreferences.clear()
    }
}