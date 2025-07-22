package com.ismael.amphibians

import android.app.Application
import com.ismael.amphibians.data.AppContainer
import com.ismael.amphibians.data.DefaultAppContainer

class AmphibianApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}