package com.vutheanh.pettranslate.feature.intro.domain.repository

import com.vutheanh.pettranslate.feature.intro.domain.model.IntroPage

interface IntroRepository {
    fun getIntroPages(): List<IntroPage>
}

