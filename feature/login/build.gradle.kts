plugins {
    alias(libs.plugins.keypick.android.feature)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.login"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)

    ksp(libs.hilt.compiler)          // Hilt 컴파일러 (ksp용)
    implementation(libs.hilt.android)

    implementation(platform(libs.androidx.compose.bom))
    implementation (libs.androidx.runtime)

    implementation (libs.androidx.hilt.navigation.compose)
}