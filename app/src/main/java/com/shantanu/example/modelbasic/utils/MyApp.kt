package com.shantanu.example.modelbasic.utils

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

//Supporting Android MultiDexApplication as per need
class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}