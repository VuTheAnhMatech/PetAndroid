package com.vutheanh.pettranslate.app

import androidx.compose.runtime.Composable
import com.vutheanh.pettranslate.app.navigation.PetTranslateNavHost
import com.vutheanh.pettranslate.core.di.AppContainer

@Composable
fun PetTranslateApp(appContainer: AppContainer) {
    PetTranslateNavHost(appContainer = appContainer)
}

