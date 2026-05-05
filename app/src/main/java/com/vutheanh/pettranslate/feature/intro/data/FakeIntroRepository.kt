package com.vutheanh.pettranslate.feature.intro.data

import com.vutheanh.pettranslate.feature.intro.domain.model.IntroIllustration
import com.vutheanh.pettranslate.feature.intro.domain.model.IntroPage
import com.vutheanh.pettranslate.feature.intro.domain.repository.IntroRepository

class FakeIntroRepository : IntroRepository {
    override fun getIntroPages(): List<IntroPage> = listOf(
        IntroPage(
            id = "intro_training",
            title = "Tailored Training For Your\nPup!",
            description = "Create a profile and personalized training\nplans for your dogs!",
            actionLabel = "Let's Get Started!",
            backgroundResName = "bg_intro01",
            illustration = IntroIllustration.CouchTraining
        ),
        IntroPage(
            id = "intro_smarter",
            title = "Train Smarter, Not\nHarder!",
            description = "Expert-led courses for both you and your\ndog, online or offline!",
            actionLabel = "Continue",
            backgroundResName = "bg_intro02",
            illustration = IntroIllustration.OutdoorPlay
        ),
        IntroPage(
            id = "intro_translate",
            title = "Break The Language\nBarrier!",
            description = "Real-time translation between you and your\ndog. Build a deeper bond!",
            actionLabel = "Start Talking!",
            backgroundResName = "bg_intro03",
            illustration = IntroIllustration.PhoneTranslator
        )
    )
}
