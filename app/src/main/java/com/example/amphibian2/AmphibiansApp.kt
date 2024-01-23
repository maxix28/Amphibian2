package com.example.amphibian2

import android.app.Application
import com.example.amphibian2.data.AppContainer
import com.example.amphibian2.data.DefaultAppContainer

class AmphibiansApp : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}