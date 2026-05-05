package com.vutheanh.pettranslate.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vutheanh.pettranslate.feature.home.domain.usecase.GetQuickActionsUseCase

class HomeViewModel(
    private val getQuickActionsUseCase: GetQuickActionsUseCase
) : ViewModel() {

    val uiState = HomeUiState(
        title = "PetTranslate",
        subtitle = "Base project is ready. Start by replacing fake data with the first real feature flow.",
        quickActions = getQuickActionsUseCase(),
        notes = listOf(
            "Single-module Clean Architecture for speed.",
            "Manual AppContainer until dependency wiring becomes painful.",
            "Compose-first. Add XML only for real platform constraints."
        )
    )

    companion object {
        fun factory(
            getQuickActionsUseCase: GetQuickActionsUseCase
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(getQuickActionsUseCase = getQuickActionsUseCase) as T
            }
        }
    }
}

