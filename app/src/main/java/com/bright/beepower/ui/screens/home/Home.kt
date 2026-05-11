package com.bright.beepower.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Token
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
import com.bright.beepower.navigation.ROUT_BUYTOKEN
import com.bright.beepower.navigation.ROUT_HISTORY
import com.bright.beepower.navigation.ROUT_NOTIFICATION
import com.bright.beepower.navigation.ROUT_PROFILE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    // 🎨 BeePower Colors
    val beeYellow = Color(0xFFF4C430)
    val lightGrey = Color(0xFFE0E0E0)
    val beeGreen = Color(0xFF1B5E20)

    Scaffold(

        containerColor = Color.Transparent,

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text = "BeePower",
                        color = beeGreen,
                        fontWeight = FontWeight.Bold
                    )
                },

                actions = {

                    IconButton(
                        onClick = {
                            navController.navigate(
                                ROUT_NOTIFICATION
                            )
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = beeGreen
                        )
                    }

                    IconButton(
                        onClick = {
                            navController.navigate(
                                ROUT_PROFILE
                            )
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = beeGreen
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = lightGrey
                )
            )
        }

    ) { padding ->

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
                )
                .padding(padding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {


                Text(
                    text = "Welcome Back",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = beeGreen
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Manage your electricity easily",
                    color = Color.DarkGray,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Balance Card
                Card(
                    modifier = Modifier.fillMaxWidth(),

                    colors = CardDefaults.cardColors(
                        containerColor = beeGreen
                    ),

                    shape = RoundedCornerShape(20.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {

                        Text(
                            text = "Current Balance",
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "KES 0",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Quick Actions
                Text(
                    text = "Quick Actions",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = beeGreen
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement =
                        Arrangement.spacedBy(16.dp)
                ) {

                    // Buy Tokens
                    HomeActionCard(
                        title = "Buy Tokens",
                        icon = Icons.Default.Token,
                        beeGreen = beeGreen
                    ) {

                        navController.navigate(
                            ROUT_BUYTOKEN
                        )
                    }

                    // History
                    HomeActionCard(
                        title = "Usage History",
                        icon = Icons.Default.History,
                        beeGreen = beeGreen
                    ) {

                        navController.navigate(
                            ROUT_HISTORY
                        )
                    }

                    // Power Status
                    HomeActionCard(
                        title = "Power Status",
                        icon = Icons.Default.Power,
                        beeGreen = beeGreen
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
fun HomeActionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    beeGreen: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = beeGreen,
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(18.dp))

            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = beeGreen
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    HomeScreen(
        rememberNavController()
    )
}