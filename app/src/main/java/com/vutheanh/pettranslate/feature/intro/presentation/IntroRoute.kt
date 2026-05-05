package com.vutheanh.pettranslate.feature.intro.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vutheanh.pettranslate.core.designsystem.theme.PetTranslateTheme
import com.vutheanh.pettranslate.feature.intro.domain.model.IntroIllustration
import com.vutheanh.pettranslate.feature.intro.domain.model.IntroPage
import com.vutheanh.pettranslate.feature.intro.domain.usecase.GetIntroPagesUseCase
import kotlinx.coroutines.launch

@Composable
fun IntroRoute(
    getIntroPagesUseCase: GetIntroPagesUseCase,
    onComplete: () -> Unit
) {
    val viewModel = remember(getIntroPagesUseCase) {
        IntroViewModel(getIntroPagesUseCase = getIntroPagesUseCase)
    }

    IntroScreen(
        uiState = viewModel.uiState,
        onComplete = onComplete
    )
}

@Composable
fun IntroScreen(
    uiState: IntroUiState,
    onComplete: () -> Unit
) {
    if (uiState.pages.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { uiState.pages.size })
    val scope = rememberCoroutineScope()
    val currentPage = pagerState.currentPage.coerceIn(0, uiState.pages.lastIndex)
    val currentIntroPage = uiState.pages[currentPage]
    val isLastPage = currentPage == uiState.pages.lastIndex
    val overlapPx = with(LocalDensity.current) { 32.dp.roundToPx() }
    val fixedControlsHeight = 120.dp
    val buttonInteractionSource = remember { MutableInteractionSource() }
    val isButtonPressed by buttonInteractionSource.collectIsPressedAsState()
    val buttonScale by animateFloatAsState(
        targetValue = if (isButtonPressed) 0.98f else 1f,
        animationSpec = spring(
            dampingRatio = 0.7f,
            stiffness = 700f
        ),
        label = "intro_button_scale"
    )

    Surface(color = Color.White) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize(),
                userScrollEnabled = false
            ) { pageIndex ->
                val page = uiState.pages[pageIndex]
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    IntroIllustrationCard(
                        backgroundResName = page.backgroundResName,
                        illustration = page.illustration,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(375f / 526f)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp, top = 24.dp, end = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Text(
                            text = page.title,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontSize = 24.sp,
                                lineHeight = 34.sp,
                                fontWeight = FontWeight.W800
                            ),
                            color = Color(0xFF171515)
                        )

                        Text(
                            text = page.description,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF6E6B67)
                        )
                    }

                    Spacer(modifier = Modifier.height(fixedControlsHeight))
                }
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .offset { IntOffset(x = 0, y = overlapPx * -1) },
                color = Color.White,
                shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
                shadowElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 54.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    PagerIndicator(
                        count = uiState.pages.size,
                        selectedIndex = currentPage
                    )

                    Button(
                        onClick = {
                            if (isLastPage) {
                                onComplete()
                            } else {
                                scope.launch {
                                    pagerState.animateScrollToPage(currentPage + 1)
                                }
                            }
                        },
                        interactionSource = buttonInteractionSource,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .offset(y = 8.dp)
                            .graphicsLayer {
                                scaleX = buttonScale
                                scaleY = buttonScale
                            },
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF644E),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = currentIntroPage.actionLabel,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PagerIndicator(
    count: Int,
    selectedIndex: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(count) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == selectedIndex) 6.dp else 5.dp)
                    .clip(CircleShape)
                    .background(
                        if (index == selectedIndex) Color(0xFF9C9C9C) else Color(0xFFD3D3D3)
                    )
            )
        }
    }
}

@Composable
private fun IntroIllustrationCard(
    backgroundResName: String,
    illustration: IntroIllustration,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val backgroundResId = context.resources.getIdentifier(
        backgroundResName,
        "drawable",
        context.packageName
    )

    if (backgroundResId != 0) {
        Image(
            painter = painterResource(id = backgroundResId),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
        return
    }

    val gradient = when (illustration) {
        IntroIllustration.CouchTraining -> Brush.linearGradient(
            colors = listOf(Color(0xFF90C39A), Color(0xFFF6EAD0))
        )
        IntroIllustration.OutdoorPlay -> Brush.verticalGradient(
            colors = listOf(Color(0xFF8EE3F3), Color(0xFFF9D792))
        )
        IntroIllustration.PhoneTranslator -> Brush.linearGradient(
            colors = listOf(Color(0xFFF3D29D), Color(0xFFF8F2E5))
        )
    }

    Box(
        modifier = modifier.background(gradient)
    ) {
        when (illustration) {
            IntroIllustration.CouchTraining -> CouchScene()
            IntroIllustration.OutdoorPlay -> OutdoorScene()
            IntroIllustration.PhoneTranslator -> PhoneScene()
        }
    }
}

@Composable
private fun CouchScene() {
    Box(modifier = Modifier.fillMaxSize()) {
        repeat(6) { index ->
            Box(
                modifier = Modifier
                    .padding(start = (index * 48).dp)
                    .fillMaxSize()
                    .background(
                        Color.Transparent
                    )
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(92.dp)
                .background(Color(0xFFF6EDE0))
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
                .size(width = 68.dp, height = 24.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFD7F1D7))
        )

        PersonAndDogCluster(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            accent = Color(0xFFF0A93C),
            mirror = false
        )

        SmallDog(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 26.dp)
        )
    }
}

@Composable
private fun OutdoorScene() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp)
                .size(72.dp)
        ) {
            drawCircle(color = Color(0xFFFFD344))
            repeat(10) { index ->
                val angle = index * 36f
                val x = center.x + kotlin.math.cos(Math.toRadians(angle.toDouble())).toFloat() * 48f
                val y = center.y + kotlin.math.sin(Math.toRadians(angle.toDouble())).toFloat() * 48f
                drawRoundRect(
                    color = Color(0xFFFFC233),
                    topLeft = Offset(x - 3f, y - 10f),
                    size = Size(6f, 20f),
                    cornerRadius = CornerRadius(4f, 4f)
                )
            }
        }

        Cloud(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 54.dp)
        )
        Cloud(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 18.dp, top = 34.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(96.dp)
                .background(Color(0xFFF4E6C9))
        )

        PersonAndDogCluster(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            accent = Color(0xFF75D2D5),
            mirror = true
        )
    }
}

@Composable
private fun PhoneScene() {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(116.dp)
                .background(Color(0xFFF6E9D7))
        )

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 42.dp, top = 28.dp)
                .width(132.dp)
                .aspectRatio(0.58f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color(0xFFA2C8F5))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(8.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
                    .size(width = 46.dp, height = 6.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(Color(0xFFDAE6F6))
            )
        }

        SpeechBubble(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 38.dp, start = 70.dp)
        )

        SmallDog(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 30.dp, bottom = 40.dp)
        )
    }
}

@Composable
private fun PersonAndDogCluster(
    modifier: Modifier = Modifier,
    accent: Color,
    mirror: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (mirror) {
            SmallDog()
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF7C9A3))
            )
            Box(
                modifier = Modifier
                    .size(width = 72.dp, height = 110.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(accent)
            )
        }

        if (!mirror) {
            SmallDog()
        }
    }
}

@Composable
private fun SmallDog(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFE1C1))
        )
        Box(
            modifier = Modifier
                .size(width = 42.dp, height = 52.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
        )
    }
}

@Composable
private fun Cloud(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((-10).dp)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.95f))
        )
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.95f))
        )
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.95f))
        )
    }
}

@Composable
private fun SpeechBubble(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 58.dp, height = 46.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF86A6FF))
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 14.dp + (index * 10).dp)
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF3F3F3F)
@Composable
private fun IntroScreenPreview() {
    PetTranslateTheme {
        IntroScreen(
            uiState = IntroUiState(
                pages = listOf(
                    IntroPage(
                        id = "intro_training",
                        title = "Tailored Training For Your Pup!",
                        description = "Create a profile and personalized training plans for your dogs!",
                        actionLabel = "Let's Get Started!",
                        backgroundResName = "bg_intro01",
                        illustration = IntroIllustration.CouchTraining
                    ),
                    IntroPage(
                        id = "intro_smarter",
                        title = "Train Smarter, Not Harder!",
                        description = "Expert-led courses for both you and your dog, online or offline!",
                        actionLabel = "Continue",
                        backgroundResName = "bg_intro02",
                        illustration = IntroIllustration.OutdoorPlay
                    ),
                    IntroPage(
                        id = "intro_translate",
                        title = "Break The Language Barrier!",
                        description = "Real-time translation between you and your dog. Build a deeper bond!",
                        actionLabel = "Start Talking!",
                        backgroundResName = "bg_intro03",
                        illustration = IntroIllustration.PhoneTranslator
                    )
                )
            ),
            onComplete = {}
        )
    }
}
