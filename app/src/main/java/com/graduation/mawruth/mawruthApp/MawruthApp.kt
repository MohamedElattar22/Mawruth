package com.graduation.mawruth.mawruthApp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MawruthApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}