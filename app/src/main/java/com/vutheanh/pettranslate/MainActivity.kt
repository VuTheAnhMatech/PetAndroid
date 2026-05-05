package com.vutheanh.pettranslate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.vutheanh.pettranslate.app.PetTranslateApp
import com.vutheanh.pettranslate.core.designsystem.theme.PetTranslateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as PetTranslateApplication).appContainer

        enableEdgeToEdge()
        setContent {
            PetTranslateTheme {
                PetTranslateApp(appContainer = appContainer)
            }
        }
    }
}

