package com.bright.beepower.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bright.beepower.data.UserSession
import com.bright.beepower.ui.screens.auth.RegisterScreen

@Composable
fun ProfileScreen(navController: NavController) {

    val beeGreen = Color(0xFF1B5E20)
    val beeYellow = Color(0xFFF4C430)

    // Refresh when session changes
    val username = UserSession.username ?: "Loading..."
    val email = UserSession.email ?: "Loading..."
    val meter = UserSession.meterNumber ?: "Loading..."
    val balance = UserSession.balance

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(beeYellow)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "PROFILE",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = beeGreen
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Username: $username")
                Text("Email: $email")
                Text("Meter: $meter")
                Text("Balance: $balance")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {

    ProfileScreen(
        rememberNavController()
    )
}

