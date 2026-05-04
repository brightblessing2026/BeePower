
package com.bright.beepower.ui.screens.otp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPScreen(
    navController: NavController,
    phone: String
) {

    var otp by remember { mutableStateOf("") }

    val green = Color(0xFF1B5E20)
    val yellow = Color(0xFFF4C430)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(yellow, Color.White)
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = green,
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Verify OTP",
                fontSize = 26.sp,
                color = green
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Code sent to $phone",
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = otp,
                onValueChange = { if (it.length <= 6) otp = it },
                label = { Text("Enter OTP") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // TODO: Firebase OTP verification here

                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = green),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Verify")
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = {
                // TODO: Resend OTP
            }) {
                Text("Resend Code", color = green)
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun OTPScreenPreview(){

    OTPScreen(rememberNavController())


}