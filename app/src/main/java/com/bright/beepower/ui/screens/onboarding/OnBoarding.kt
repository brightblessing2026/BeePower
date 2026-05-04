package com.bright.beepower.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

// Data class for each onboarding slide
data class OnboardPage(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val pages = listOf(
        OnboardPage(
            "Instant Top-up",
            "Buy electricity tokens instantly via M-Pesa at any time of the day.",
            Icons.Default.FlashOn,
            Color(0xFFF4C430)
        ),
        OnboardPage(
            "Track Usage",
            "Monitor your kWh consumption and get notified before your balance runs out.",
            Icons.Default.NotificationsActive,
            Color(0xFF1B5E20)
        ),
        OnboardPage(
            "Smart History",
            "Keep all your token receipts in one place. Never lose a token code again.",
            Icons.Default.History,
            Color(0xFF1B5E20)
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Skip Button
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = { navController.navigate("login") }) {
                Text("Skip", color = Color.Gray)
            }
        }

        // Pager Content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { position ->
            OnboardContent(pages[position])
        }

        // Bottom Section (Indicators and Button)
        Column(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page Indicators
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(pages.size) { it ->
                    val isSelected = pagerState.currentPage == it
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 12.dp else 8.dp)
                            .background(
                                color = if (isSelected) Color(0xFF1B5E20) else Color.LightGray,
                                shape = RoundedCornerShape(50)
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Next/Get Started Button
            Button(
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        navController.navigate("login")
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    if (pagerState.currentPage == pages.size - 1) "Get Started" else "Next",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun OnboardContent(page: OnboardPage) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier.size(200.dp),
            color = page.color.copy(alpha = 0.1f),
            shape = RoundedCornerShape(100.dp)
        ) {
            Icon(
                imageVector = page.icon,
                contentDescription = null,
                modifier = Modifier.padding(40.dp).fillMaxSize(),
                tint = page.color
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = page.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = page.description,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview(){

    OnBoardingScreen(rememberNavController())


}