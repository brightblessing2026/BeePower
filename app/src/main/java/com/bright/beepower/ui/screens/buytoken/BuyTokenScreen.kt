package com.bright.beepower.ui.screens.buytoken

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyTokenScreen(navController: NavController) {

    var amount by remember { mutableStateOf("") }

    val context = LocalContext.current

    // 🎨 Colors
    val beeYellow = Color(0xFFF4C430)
    val lightGrey = Color(0xFFE0E0E0)
    val beeGreen = Color(0xFF1B5E20)

    Scaffold(
        containerColor = Color.Transparent,

        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = "Buy Tokens",
                        color = beeGreen
                    )
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
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

        // 🌈 Background
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
                    .padding(24.dp)
            ) {

                Text(
                    text = "Enter Amount (KES)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = beeGreen
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = amount,

                    onValueChange = {
                        amount = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    placeholder = {
                        Text("e.g. 500")
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),

                    shape = RoundedCornerShape(12.dp),

                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                // ✅ Mpesa Button
                Button(

                    onClick = {

                        if (amount.isBlank()) {

                            Toast.makeText(
                                context,
                                "Please enter amount",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            val simToolKitLaunchIntent =
                                context.packageManager
                                    .getLaunchIntentForPackage(
                                        "com.android.stk"
                                    )

                            if (simToolKitLaunchIntent != null) {

                                context.startActivity(
                                    simToolKitLaunchIntent
                                )

                            } else {

                                Toast.makeText(
                                    context,
                                    "SIM Toolkit not found",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = beeGreen
                    ),

                    shape = RoundedCornerShape(10.dp),

                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = "Pay via Mpesa",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BuyTokenScreenPreview() {

    BuyTokenScreen(
        rememberNavController()
    )
}
