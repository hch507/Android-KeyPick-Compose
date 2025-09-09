package com.example.keypick_compose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KeypickApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}