package com.example.network.retrofit

import android.util.Log
import com.example.network.BlogInfoNetworkDataSource
import com.example.network.common.MY_BLOG
import com.example.network.model.LoginOrCntDto
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


private interface RetrofitBlogInfoNetworkApi{
    @GET("NVisitorgp4Ajax.nhn")
    suspend fun getBlogInfo(
        @Query("blogId") blogId : String
    ):Response<LoginOrCntDto>
}

@Singleton
class RetrofitBlogInfoNetwork @Inject constructor(
) : BlogInfoNetworkDataSource {

    private val networkApi =
        Retrofit.Builder()
            .baseUrl(MY_BLOG.MY_BASE_URL)
            .addConverterFactory(TikXmlConverterFactory.create(TikXml.Builder().exceptionOnUnreadXml(false).build()))
            .build()
            .create(RetrofitBlogInfoNetworkApi::class.java)
    override suspend fun getUserBlogInfo(blogId: String) : LoginOrCntDto {
        val response = networkApi.getBlogInfo(blogId)
        return if (response.isSuccessful) {
            Log.d("test_repository", "getUserBlogData: ${response.body()}")
            response.body() ?: throw Exception("Response body is null")

        } else {
            throw Exception("Network call failed with code: ${response.code()}")
        }
    }
}