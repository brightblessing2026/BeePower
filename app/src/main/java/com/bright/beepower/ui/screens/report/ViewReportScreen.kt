package com.bright.beepower.ui.screens.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bright.beepower.models.Issue
import com.google.firebase.auth.FirebaseAuth   // ✅ ADDED
import com.google.firebase.database.*

@Composable
fun ViewReportScreen(navController: NavController) {

    val beeYellow = Color(0xFFF4C430)
    val lightGrey = Color(0xFFE0E0E0)
    val beeGreen = Color(0xFF1B5E20)

    val database =
        FirebaseDatabase.getInstance()
            .reference
            .child("Issues")

    val issueList = remember { mutableStateListOf<Issue>() }

    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid   // ✅ ADDED

    val roleRef = FirebaseDatabase.getInstance()
        .reference
        .child("users")
        .child(currentUserId ?: "")
        .child("role")

    var userRole by remember { mutableStateOf("user") }

    // ✅ GET USER ROLE
    LaunchedEffect(Unit) {
        roleRef.get().addOnSuccessListener { snapshot ->
            userRole = snapshot.value?.toString() ?: "user"
        }
    }

    // ✅ LOAD ISSUES
    LaunchedEffect(Unit) {

        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                issueList.clear()

                for (snap in snapshot.children) {

                    val issue = snap.getValue(Issue::class.java)

                    if (issue != null) {

                        // 🔥 FILTER LOGIC (IMPORTANT)
                        if (userRole == "admin") {
                            issueList.add(issue)   // admin sees all
                        } else {
                            if (issue.userId == currentUserId) {
                                issueList.add(issue) // user sees own only
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
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
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            Text(
                text = "Reported Issues",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = beeGreen
            )

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {

                items(issueList) { issue ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = issue.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = beeGreen
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = issue.description,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Location: ${issue.location}",
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewReportScreenPreview() {
    ViewReportScreen(rememberNavController())
}