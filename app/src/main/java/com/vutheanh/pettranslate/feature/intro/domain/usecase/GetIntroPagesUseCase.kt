package com.vutheanh.pettranslate.feature.intro.domain.usecase

import com.vutheanh.pettranslate.feature.intro.domain.model.IntroPage
import com.vutheanh.pettranslate.feature.intro.domain.repository.IntroRepository

class GetIntroPagesUseCase(
    private val introRepository: IntroRepository
) {
    operator fun invoke(): List<IntroPage> = introRepository.getIntroPages()
}

