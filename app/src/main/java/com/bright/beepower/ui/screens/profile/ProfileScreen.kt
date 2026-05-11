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
import com.bright.beepower.ui.screens.onboarding.OnBoardingScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun ProfileScreen(navController: NavController) {

    val beeGreen = Color(0xFF1B5E20)
    val beeYellow = Color(0xFFF4C430)

    var username by remember { mutableStateOf("Loading...") }
    var email by remember { mutableStateOf("Loading...") }
    var meter by remember { mutableStateOf("Loading...") }
    var balance by remember { mutableStateOf("Loading...") }

    // 🔥 FETCH USER DATA FROM FIREBASE
    LaunchedEffect(Unit) {

        val uid = FirebaseAuth.getInstance().currentUser?.uid

        if (uid != null) {

            FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid)
                .get()
                .addOnSuccessListener { snapshot ->

                    username = snapshot.child("username").value?.toString() ?: "N/A"
                    email = snapshot.child("email").value?.toString() ?: "N/A"
                    meter = snapshot.child("meterNumber").value?.toString() ?: "N/A"
                    balance = snapshot.child("balance").value?.toString() ?: "0"

                    // ✅ STORE IN SESSION (fix dashboard/profile skip issues)
                    UserSession.username = username
                    UserSession.email = email
                    UserSession.meterNumber = meter
                    balance = snapshot.child("balance")
                        .getValue(Long::class.java)
                        ?.toString()
                        ?: "0"
                }
        }
    }

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

        Spacer(modifier = Modifier.height(24.dp))

        // 🔴 LOGOUT BUTTON (ADDED ONLY FUNCTIONALITY)
        Button(
            onClick = {
                FirebaseAuth.getInstance().signOut()
                navController.navigate("login") {
                    popUpTo(0)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = beeGreen
            )
        ) {
            Text("Logout", color = Color.White)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){

    ProfileScreen(rememberNavController())


}