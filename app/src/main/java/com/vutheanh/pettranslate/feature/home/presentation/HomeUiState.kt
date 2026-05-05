package com.vutheanh.pettranslate.feature.home.presentation

import com.vutheanh.pettranslate.feature.home.domain.model.QuickAction

data class HomeUiState(
    val title: String,
    val subtitle: String,
    val quickActions: List<QuickAction>,
    val notes: List<String>
)

