package com.bright.beepower.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val beeYellow = Color(0xFFF4C430)
    val beeGreen = Color(0xFF1B5E20)

    LaunchedEffect(key1 = true) {
        delay(2500)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(beeYellow),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.ElectricBolt,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = beeGreen
            )
            Text(
                text = "BeePower",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = beeGreen
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){

    SplashScreen(rememberNavController())


}