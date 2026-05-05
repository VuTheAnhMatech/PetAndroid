package com.vutheanh.pettranslate.feature.home.domain.usecase

import com.vutheanh.pettranslate.feature.home.domain.model.QuickAction
import com.vutheanh.pettranslate.feature.home.domain.repository.HomeRepository

class GetQuickActionsUseCase(
    private val homeRepository: HomeRepository
) {
    operator fun invoke(): List<QuickAction> = homeRepository.getQuickActions()
}

