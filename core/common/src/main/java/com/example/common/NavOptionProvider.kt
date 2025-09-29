package com.example.common

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import kotlin.reflect.KClass

object NavOptionProvider {
    fun popUpToStartGraph(navController: NavController) = navOptions {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    @SuppressLint("RestrictedApi")
    fun <T : Any> clearBackStackAndLaunchSingle(destinationType: KClass<T>) = navOptions {
        // String 대신 클래스 타입(KClass)을 사용하여 popUpTo를 호출합니다.
        popUpTo(destinationType) {
            inclusive = true
        }
        launchSingleTop = true
    }
}