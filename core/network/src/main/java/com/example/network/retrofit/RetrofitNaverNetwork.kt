package com.example.network.retrofit

import com.example.network.NaverNetworkDataSource
import com.example.network.model.BlogKeywordParam
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject

private interface RetrofitNaverNetworkApi{

    suspend fun getKeywordData(
        @Header("Content-Type") content_type: String,
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Body request: BlogKeywordParam
    )


    @GET("search/blog.json")
    suspend fun getBlogTotal(
        @Header("X-Naver-Client-Id") client_id: String,
        @Header("X-Naver-Client-Secret") client_secret: String,
        @Query("display") display: Int,
        @Query("query") searhTerm: String?,
        @Query("sort") sort: String
    )
}

private const val NAVER_BASE_URL = "https://openapi.naver.com/v1/"

class RetrofitNaverNetwork @Inject constructor(

) : NaverNetworkDataSource {
    private val networkApi =
        Retrofit.Builder()
            .baseUrl(NAVER_BASE_URL)
            .build()
            .create(RetrofitNaverNetworkApi::class.java)

    override suspend fun getMonthlySearchVolume() {
        TODO("Not yet implemented")
    }

    override suspend fun getRecentMonthlySearchVolume() {
        TODO("Not yet implemented")
    }

    override suspend fun getBlogPostRank() {
        TODO("Not yet implemented")
    }

}