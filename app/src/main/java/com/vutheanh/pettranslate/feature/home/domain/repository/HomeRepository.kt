package com.vutheanh.pettranslate.feature.home.domain.repository

import com.vutheanh.pettranslate.feature.home.domain.model.QuickAction

interface HomeRepository {
    fun getQuickActions(): List<QuickAction>
}

