import java.util.Properties

plugins {

    alias(libs.plugins.keypick.android.library)
    alias(libs.plugins.ksp)
    id ("kotlin-kapt")
    alias(libs.plugins.kotlin.serialization)
}
android {
    namespace = "com.example.network"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "NAVER_SEARCH_AD_BASE_URL", "\"${localProperty("NAVER_SEARCH_AD_BASE_URL")}\"")
        buildConfigField("String", "NAVER_SEARCH_AD_API_KEY", "\"${localProperty("NAVER_SEARCH_AD_API_KEY")}\"")
        buildConfigField("String", "NAVER_SEARCH_AD_CUSTOMER_ID", "\"${localProperty("NAVER_SEARCH_AD_CUSTOMER_ID")}\"")
        buildConfigField("String", "NAVER_SEARCH_AD_SECRET_KEY", "\"${localProperty("NAVER_SEARCH_AD_SECRET_KEY")}\"")
        buildConfigField("String", "NAVER_API_BASE_URL", "\"${localProperty("NAVER_API_BASE_URL")}\"")
        buildConfigField("String", "NAVER_SEARCH_CLINENT_ID", "\"${localProperty("NAVER_SEARCH_CLINENT_ID")}\"")
        buildConfigField("String", "NAVER_SEARCH_CLIENT_PW", "\"${localProperty("NAVER_SEARCH_CLIENT_PW")}\"")
        buildConfigField("String", "NAVER_BLOG_CLIENT_ID", "\"${localProperty("NAVER_BLOG_CLIENT_ID")}\"")
        buildConfigField("String", "NAVER_BLOG_CLIENT_PW", "\"${localProperty("NAVER_BLOG_CLIENT_PW")}\"")
        buildConfigField("String", "NAVER_MY_BLOG_BASE_URL", "\"${localProperty("NAVER_MY_BLOG_BASE_URL")}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

fun localProperty(key: String): String {
    val properties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    }
    return properties.getProperty(key) ?: throw GradleException("Property $key is not defined in local.properties")
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)

    api(libs.retrofit.kotlin.serialization)
    api(libs.retrofit.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp)

    implementation ("com.tickaroo.tikxml:annotation:0.8.13")
    implementation ("com.tickaroo.tikxml:core:0.8.13")
    implementation ("com.tickaroo.tikxml:retrofit-converter:0.8.13")

    kapt ("com.tickaroo.tikxml:processor:0.8.13")
}