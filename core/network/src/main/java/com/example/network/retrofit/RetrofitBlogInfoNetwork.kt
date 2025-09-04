package com.example.network.retrofit

import com.example.network.BlogInfoNetworkDataSource
import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton


private interface RetrofitBlogInfoNetworkApi{
    @GET("NVisitorgp4Ajax.nhn")
    suspend fun getBlogInfo()
}


private const val BLOG_INFO_BASE_URL = "https://blog.naver.com/"

@Singleton
internal class RetrofitBlogInfoNetwork @Inject constructor(
) : BlogInfoNetworkDataSource {

    private val networkApi =
        Retrofit.Builder()
            .baseUrl(BLOG_INFO_BASE_URL)
            .build()
            .create(RetrofitBlogInfoNetworkApi::class.java)
    override suspend fun getUserBlogInfo() {
        networkApi.getBlogInfo()
    }
}