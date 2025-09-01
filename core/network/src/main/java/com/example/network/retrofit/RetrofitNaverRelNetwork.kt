package com.example.network.retrofit

import com.example.network.NaverRelNetworkDataSource
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject


private interface RetrofitNaverRelNetworkApi{
    @Headers("Cache-Control: no-cache", "Pragma: no-cache")
    @GET("keywordstool")
    suspend fun getRelKwdStatTest(
        @Header("Content-Type") content_type : String,
        @Header("X-Timestamp") x_timestamp : String,
        @Header("X-API-KEY") api_key : String,
        @Header("X-Customer") x_customer: String,
        @Header("X-Signature") x_signature : String,
        @Query("hintKeywords") hintKeywords: String?,
        @Query("showDetail") showDetail: Int = 1,
    )
}

private const val NAVER_REL_BASE_URL = "https://api.searchad.naver.com/"
class RetrofitNaverRelNetwork @Inject constructor(

) : NaverRelNetworkDataSource{
    private val networkApi =
        Retrofit.Builder()
            .baseUrl(NAVER_REL_BASE_URL)
            .build()
            .create(RetrofitNaverRelNetworkApi::class.java)
    override suspend fun getKeywordRel() {

    }

}