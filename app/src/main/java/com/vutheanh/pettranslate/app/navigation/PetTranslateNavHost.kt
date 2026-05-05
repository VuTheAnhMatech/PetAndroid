package com.vutheanh.pettranslate.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vutheanh.pettranslate.core.di.AppContainer
import com.vutheanh.pettranslate.feature.intro.presentation.IntroRoute
import com.vutheanh.pettranslate.feature.main.presentation.MainTabRoute
import com.vutheanh.pettranslate.feature.splash.presentation.SplashRoute

@Composable
fun PetTranslateNavHost(appContainer: AppContainer) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PetTranslateDestination.Splash.name
    ) {
        composable(PetTranslateDestination.Splash.name) {
            SplashRoute(
                onFinished = {
                    navController.navigate(PetTranslateDestination.Intro.name) {
                        popUpTo(PetTranslateDestination.Splash.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(PetTranslateDestination.Intro.name) {
            IntroRoute(
                getIntroPagesUseCase = appContainer.getIntroPagesUseCase,
                onComplete = {
                    navController.navigate(PetTranslateDestination.Main.name) {
                        popUpTo(PetTranslateDestination.Intro.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(PetTranslateDestination.Main.name) {
            MainTabRoute()
        }
    }
}
