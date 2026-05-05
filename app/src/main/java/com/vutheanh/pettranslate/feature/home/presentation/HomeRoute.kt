package com.vutheanh.pettranslate.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vutheanh.pettranslate.core.designsystem.theme.PetTranslateTheme
import com.vutheanh.pettranslate.feature.home.domain.model.QuickAction
import com.vutheanh.pettranslate.feature.home.domain.usecase.GetQuickActionsUseCase

@Composable
fun HomeRoute(
    getQuickActionsUseCase: GetQuickActionsUseCase
) {
    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModel.factory(getQuickActionsUseCase = getQuickActionsUseCase)
    )

    HomeScreen(uiState = viewModel.uiState)
}

@Composable
fun HomeScreen(uiState: HomeUiState) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = uiState.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = uiState.subtitle,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }

            item {
                SectionTitle(title = "Build next")
            }

            items(uiState.quickActions, key = QuickAction::id) { action ->
                QuickActionCard(action = action)
            }

            item {
                SectionTitle(title = "Architecture notes")
            }

            items(uiState.notes, key = { it }) { note ->
                Card {
                    Text(
                        text = note,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun QuickActionCard(action: QuickAction) {
    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = action.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = action.status,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Text(
                text = action.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    PetTranslateTheme {
        HomeScreen(
            uiState = HomeUiState(
                title = "PetTranslate",
                subtitle = "Base project is ready. Start by replacing fake data with the first real feature flow.",
                quickActions = listOf(
                    QuickAction(
                        id = "voice_translate",
                        title = "Voice Translate Flow",
                        description = "Build the first talk-to-pet conversation path with audio input and translated output.",
                        status = "Next"
                    )
                ),
                notes = listOf(
                    "Single-module Clean Architecture for speed.",
                    "Compose-first with feature-local components."
                )
            )
        )
    }
}
