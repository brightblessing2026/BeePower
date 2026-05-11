package com.bright.beepower.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bright.beepower.data.UserSession
import com.bright.beepower.ui.screens.home.HomeScreen

@Composable
fun DashBoardScreen(navController: NavController) {

    val beeGreen = Color(0xFF166501)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFFF4C430),
                        Color(0xFFD3D3D3),
                        Color(0xFFADC9A6)
                    )
                )
            )
            .padding(24.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = "Hello, ${UserSession.username ?: "User"}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Text(
                    text = "Meter: ${UserSession.meterNumber ?: "N/A"}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(onClick = { navController.navigate("notification") }) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = beeGreen)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth().height(180.dp),
            colors = CardDefaults.cardColors(containerColor = beeGreen),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text("Current Balance", color = Color.White.copy(alpha = 0.8f))
                Text("45.80 kWh", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
                Text("Last update: Just now", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text("Quick Actions", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            DashboardItem("Buy", Icons.Default.FlashOn, Color(0xFFF4C430)) {
                navController.navigate("buytoken")
            }

            DashboardItem("History", Icons.Default.History, Color.LightGray) {
                navController.navigate("history")
            }

            DashboardItem("Profile", Icons.Default.Person, Color.LightGray) {
                navController.navigate("profile")
            }
        }
    }
}

@Composable
fun DashboardItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(icon, contentDescription = null, tint = Color.Black)
        }
        Text(label)
    }
}


@Preview(showBackground = true)
@Composable
fun DashBoardScreenPreview() {

    DashBoardScreen(
        rememberNavController()
    )
}