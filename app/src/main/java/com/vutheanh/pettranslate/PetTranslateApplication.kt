package com.vutheanh.pettranslate

import android.app.Application
import com.vutheanh.pettranslate.core.di.AppContainer
import com.vutheanh.pettranslate.core.di.DefaultAppContainer

class PetTranslateApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}

