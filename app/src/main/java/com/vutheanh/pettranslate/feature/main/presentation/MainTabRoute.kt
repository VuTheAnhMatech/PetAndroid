package com.vutheanh.pettranslate.feature.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vutheanh.pettranslate.core.designsystem.theme.PetTranslateTheme
import com.vutheanh.pettranslate.core.extensions.toDrawableRes

@Composable
fun MainTabRoute() {
    var selectedTab by remember { mutableStateOf(MainTab.Translate) }

    MainTabScreen(
        selectedTab = selectedTab,
        onTabSelected = { selectedTab = it }
    )
}

@Composable
fun MainTabScreen(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFF7F5F1),
        bottomBar = {
            NavigationBar(
                modifier = Modifier.navigationBarsPadding(),
                containerColor = Color.White
            ) {
                MainTab.entries.forEach { tab ->
                    val isSelected = selectedTab == tab
                    val iconResId = rememberDrawableResId(name = tab.image)

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { onTabSelected(tab) },
                        icon = {
                            if (iconResId != 0) {
                                Icon(
                                    painter = painterResource(id = iconResId),
                                    contentDescription = tab.label,
                                    modifier = Modifier.size(22.dp),
                                    tint = if (isSelected) Color(0xFF123A69) else Color(0xFF8E8E93)
                                )
                            }
                        },
                        label = {
                            Text(
                                text = tab.label,
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF123A69),
                            selectedTextColor = Color(0xFF123A69),
                            unselectedIconColor = Color(0xFF8E8E93),
                            unselectedTextColor = Color(0xFF8E8E93),
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = Color(0xFFF7F5F1)
        ) {
            MainTabPlaceholder(tab = selectedTab)
        }
    }
}

@Composable
private fun MainTabPlaceholder(tab: MainTab) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 18.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = tab.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF171515)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = tab.placeholder,
            fontSize = 14.sp,
            color = Color(0xFF6E6B67)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0DDD7))
                        )
                    }
                }

                Text(
                    text = "${tab.title} screen placeholder",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF3B3A38)
                )
            }
        }
    }
}

@Composable
private fun rememberDrawableResId(name: String): Int {
    val context = LocalContext.current

    return remember(name, context) {
        name.toDrawableRes(context)
    }
}

enum class MainTab(
    val label: String,
    val title: String,
    val placeholder: String,
    val image: String
) {
    Translate(
        label = "Translate",
        title = "Pet Translate",
        placeholder = "Temporary placeholder for the translate flow.",
        image = "img_translate"
    ),
    Training(
        label = "Training",
        title = "Training",
        placeholder = "Temporary placeholder for courses and routines.",
        image = "img_tranining"
    ),
    Sound(
        label = "Sound",
        title = "Pet Sounds",
        placeholder = "Temporary placeholder for the sound board.",
        image = "img_sound"
    ),
    Game(
        label = "Game",
        title = "Game",
        placeholder = "Temporary placeholder for games and activities.",
        image = "img_game"
    ),
    Setting(
        label = "Setting",
        title = "Setting",
        placeholder = "Temporary placeholder for app settings.",
        image = "img_setting"
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTabScreenPreview() {
    PetTranslateTheme {
        MainTabScreen(
            selectedTab = MainTab.Translate,
            onTabSelected = {}
        )
    }
}
