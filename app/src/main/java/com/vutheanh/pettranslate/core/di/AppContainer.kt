package com.vutheanh.pettranslate.core.di

import com.vutheanh.pettranslate.feature.home.data.FakeHomeRepository
import com.vutheanh.pettranslate.feature.home.domain.repository.HomeRepository
import com.vutheanh.pettranslate.feature.home.domain.usecase.GetQuickActionsUseCase
import com.vutheanh.pettranslate.feature.intro.data.FakeIntroRepository
import com.vutheanh.pettranslate.feature.intro.domain.repository.IntroRepository
import com.vutheanh.pettranslate.feature.intro.domain.usecase.GetIntroPagesUseCase

interface AppContainer {
    val getQuickActionsUseCase: GetQuickActionsUseCase
    val getIntroPagesUseCase: GetIntroPagesUseCase
}

class DefaultAppContainer : AppContainer {
    private val homeRepository: HomeRepository = FakeHomeRepository()
    private val introRepository: IntroRepository = FakeIntroRepository()

    override val getQuickActionsUseCase: GetQuickActionsUseCase =
        GetQuickActionsUseCase(homeRepository = homeRepository)

    override val getIntroPagesUseCase: GetIntroPagesUseCase =
        GetIntroPagesUseCase(introRepository = introRepository)
}
