package com.example.network.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.network.BuildConfig
import java.time.LocalDate
import java.util.Base64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.text.Charsets.UTF_8

object API {
    val BASE_URL: String = BuildConfig.NAVER_SEARCH_AD_BASE_URL
    const val Content_Type: String = "application/json"
    var X_Timestamp = System.currentTimeMillis().toString()
    val X_API_KEY: String = BuildConfig.NAVER_SEARCH_AD_API_KEY
    val X_customer: String = "2776436"
    val X_secret: String = BuildConfig.NAVER_SEARCH_AD_SECRET_KEY

    fun updateTimestamp() {
        X_Timestamp = System.currentTimeMillis().toString()
    }
}

object SEARCH_API {

    val BASE_URL: String = BuildConfig.NAVER_API_BASE_URL
    val CLIENT_ID: String = BuildConfig.NAVER_SEARCH_CLINENT_ID
    val CLIENT_PW: String = BuildConfig.NAVER_SEARCH_CLIENT_PW
    val START_DATE: String = "2022-01-01"

    @RequiresApi(Build.VERSION_CODES.O)
    var END_DATE = LocalDate.now().toString()
    const val TIMEUNIT: String = "month"

}

object BLOG_API {
    const val BASE_URL: String = BuildConfig.NAVER_API_BASE_URL
    const val CLIENT_ID = BuildConfig.NAVER_BLOG_CLIENT_ID
    const val CLIENT_PW = BuildConfig.NAVER_BLOG_CLIENT_PW
    const val SORT = "date"
    const val SORT2 = "sim"
}

object MY_BLOG {
    const val MY_BASE_URL: String = BuildConfig.NAVER_MY_BLOG_BASE_URL
}


object Signature {
    val method = "GET"
    val uri = "/keywordstool"

    @RequiresApi(Build.VERSION_CODES.O)
    fun generate(timestamp: String, method: String, uri: String, secretKey: String): String {
        val message = "$timestamp.$method.$uri"
        val secretKeyBytes = secretKey.toByteArray(UTF_8)
        val messageBytes = message.toByteArray(UTF_8)
        val signingKey = SecretKeySpec(secretKeyBytes, "HmacSHA256")
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(signingKey)
        val rawHmac = mac.doFinal(messageBytes)
        return Base64.getEncoder().encodeToString(rawHmac)
    }
}