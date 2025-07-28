package com

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure


class AndroidApplicationConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")

            extensions.configure<ApplicationExtension> {
                compileSdk = 35
                defaultConfig.minSdk= 24
                defaultConfig.targetSdk = 35

                compileOptions.sourceCompatibility = JavaVersion.VERSION_11
                compileOptions.targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }

}