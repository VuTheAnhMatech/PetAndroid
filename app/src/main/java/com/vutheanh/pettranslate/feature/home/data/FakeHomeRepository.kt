package com.vutheanh.pettranslate.feature.home.data

import com.vutheanh.pettranslate.feature.home.domain.model.QuickAction
import com.vutheanh.pettranslate.feature.home.domain.repository.HomeRepository

class FakeHomeRepository : HomeRepository {
    override fun getQuickActions(): List<QuickAction> = listOf(
        QuickAction(
            id = "voice_translate",
            title = "Voice Translate Flow",
            description = "Build the first talk-to-pet conversation path with audio input and translated output.",
            status = "Next"
        ),
        QuickAction(
            id = "chat_history",
            title = "Session History",
            description = "Store and replay past translation sessions once the core flow is stable.",
            status = "Planned"
        ),
        QuickAction(
            id = "camera_mode",
            title = "Camera + Image Mode",
            description = "Add visual context after the voice path is in place.",
            status = "Later"
        )
    )
}

