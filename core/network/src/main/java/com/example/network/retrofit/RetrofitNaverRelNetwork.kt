package com.example.network.retrofit

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.network.NaverRelNetworkDataSource
import com.example.network.common.API
import com.example.network.common.BLOG_API
import com.example.network.common.Signature
import com.example.network.model.RelKeywordDto
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject


private interface RetrofitNaverRelNetworkApi{
    @Headers("Cache-Control: no-cache", "Pragma: no-cache")
    @GET("keywordstool")
    suspend fun fetchRelKwdStat(
        @Header("Content-Type") content_type : String,
        @Header("X-Timestamp") x_timestamp : String,
        @Header("X-API-KEY") api_key : String,
        @Header("X-Customer") x_customer: String,
        @Header("X-Signature") x_signature : String,
        @Query("hintKeywords") hintKeywords: String?,
        @Query("showDetail") showDetail: Int = 1,
    ) : Response<RelKeywordDto>
}


class RetrofitNaverRelNetwork @Inject constructor(
) : NaverRelNetworkDataSource{
    private val networkApi =
        Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(RetrofitNaverRelNetworkApi::class.java)
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun fetchKeywordRel(keyword : String): RelKeywordDto {
        API.updateTimestamp()
        val response = networkApi.fetchRelKwdStat(
            content_type = API.Content_Type,
            x_timestamp = API.X_Timestamp,
            api_key = API.X_API_KEY,
            x_customer = API.X_customer,
            x_signature = Signature.generate(
                API.X_Timestamp,
                Signature.method,
                Signature.uri,
                API.X_secret
            ),
            hintKeywords = keyword
        )
        return if (response.isSuccessful) {
            Log.d("test_repository", "fetchKeywordRel: ${response.body()}")
            response.body() ?: throw Exception("Response body is null")

        } else {
            throw Exception("Network call failed with code: ${response.code()}")
        }
    }

}