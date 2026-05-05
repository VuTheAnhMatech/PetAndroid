package com.vutheanh.pettranslate.feature.intro.domain.model

data class IntroPage(
    val id: String,
    val title: String,
    val description: String,
    val actionLabel: String,
    val backgroundResName: String,
    val illustration: IntroIllustration
)

enum class IntroIllustration {
    CouchTraining,
    OutdoorPlay,
    PhoneTranslator
}
