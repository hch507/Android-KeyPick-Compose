package com.example.network.retrofit

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.network.NaverNetworkDataSource
import com.example.network.common.API
import com.example.network.common.BLOG_API
import com.example.network.common.SEARCH_API
import com.example.network.model.BlogKeywordParam
import com.example.network.model.BlogSearchDto
import com.example.network.model.KeywordGroup
import com.example.network.model.MonthlySearchDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Inject
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private interface RetrofitNaverNetworkApi{
    @POST("datalab/search")
    suspend fun fetchMonthlySearch(
        @Header("Content-Type") content_type: String,
        @Header("X-Naver-Client-Id") clientId: String,
        @Header("X-Naver-Client-Secret") clientSecret: String,
        @Body request: BlogKeywordParam
    ): Response<MonthlySearchDto>


    @GET("search/blog.json")
    suspend fun fetchBlogSearch(
        @Header("X-Naver-Client-Id") client_id: String,
        @Header("X-Naver-Client-Secret") client_secret: String,
        @Query("display") display: Int,
        @Query("query") searhTerm: String?,
        @Query("sort") sort: String
    ) : Response<BlogSearchDto>
}


class RetrofitNaverNetwork @Inject constructor(
) : NaverNetworkDataSource {
    private val networkApi =
        Retrofit.Builder()
            .baseUrl(SEARCH_API.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RetrofitNaverNetworkApi::class.java)

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun fetchMonthlySearchVolume(keyword : String) : MonthlySearchDto {

        val keywordGroups = listOf(
            KeywordGroup(
                groupName = keyword,
                keywords = listOf(keyword)
            )
        )

        val request = BlogKeywordParam(
            startDate = SEARCH_API.START_DATE,
            endDate = SEARCH_API.END_DATE,
            timeUnit = SEARCH_API.TIMEUNIT,
            keywordGroups = keywordGroups
        )
        val response = networkApi.fetchMonthlySearch(
            API.Content_Type,
            SEARCH_API.CLIENT_ID,
            SEARCH_API.CLIENT_PW, request
        )
        return if (response.isSuccessful) {
            Log.d("test_repository", "fetchMonthlySearch: ${response.body()}")
            response.body() ?: throw Exception("Response body is null")

        } else {
            throw Exception("Network call failed with code: ${response.code()}")
        }
    }

    override suspend fun getRecentMonthlySearchVolume() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchBlogPostRank(keyword : String) : BlogSearchDto {
        val response = networkApi.fetchBlogSearch(
            client_id = BLOG_API.CLIENT_ID,
            client_secret = BLOG_API.CLIENT_PW,
            display = 100, searhTerm = keyword,
            sort = BLOG_API.SORT2
        )
        return if (response.isSuccessful) {
            Log.d("test_repository", "fetchMonthlySearch: ${response.body()}")
            response.body() ?: throw Exception("Response body is null")

        } else {
            throw Exception("Network call failed with code: ${response.code()}")
        }
    }

}