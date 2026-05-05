package com.vutheanh.pettranslate.feature.intro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vutheanh.pettranslate.feature.intro.domain.usecase.GetIntroPagesUseCase

class IntroViewModel(
    private val getIntroPagesUseCase: GetIntroPagesUseCase
) : ViewModel() {

    val uiState = IntroUiState(
        pages = getIntroPagesUseCase()
    )

    companion object {
        fun factory(
            getIntroPagesUseCase: GetIntroPagesUseCase
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return IntroViewModel(
                    getIntroPagesUseCase = getIntroPagesUseCase
                ) as T
            }
        }
    }
}

