plugins {
    id("java-library")
    `kotlin-dsl`
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
}
tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}
gradlePlugin{
    plugins{
        register("androidApplication"){
            id = libs.plugins.keypick.android.application.get().pluginId
            implementationClass = "com.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary"){
            id = libs.plugins.keypick.android.library.get().pluginId
            implementationClass = "com.AndroidLibraryConventionPlugin"
        }
        register("androidFeature"){
            id = libs.plugins.keypick.android.feature.get().pluginId
            implementationClass = "com.AndroidFeatureConventionPlugin"
        }
    }
}
