package com.bright.beepower.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.bright.beepower.navigation.ROUT_DASHBOARD
import com.bright.beepower.navigation.ROUT_ONBOARDING

import com.google.firebase.auth.FirebaseAuth

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val beeYellow = Color(0xFFF4C430)
    val lightGrey = Color(0xFFE0E0E0)
    val beeGreen = Color(0xFF1B5E20)

    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {

        delay(2000)

        if (auth.currentUser != null) {

            navController.navigate(
                ROUT_DASHBOARD
            )

        } else {

            navController.navigate(
                ROUT_ONBOARDING
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        beeYellow,
                        lightGrey
                    )
                )
            ),

        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "BeePower",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = beeGreen
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){

    SplashScreen(rememberNavController())


}