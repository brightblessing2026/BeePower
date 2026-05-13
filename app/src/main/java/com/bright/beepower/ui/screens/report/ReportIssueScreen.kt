package com.bright.beepower.ui.screens.report

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bright.beepower.models.Issue
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth   // ✅ ADDED

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportIssueScreen(navController: NavController) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    val context = LocalContext.current

    val database =
        FirebaseDatabase.getInstance()
            .reference
            .child("Issues")

    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid  // ✅ ADDED

    // 🎨 Colors
    val beeYellow = Color(0xFFF4C430)
    val lightGrey = Color(0xFFE0E0E0)
    val beeGreen = Color(0xFF1B5E20)

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
                text = "Report Power Issue",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = beeGreen
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Issue Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {

                    if (
                        title.isBlank() ||
                        description.isBlank() ||
                        location.isBlank()
                    ) {
                        Toast.makeText(
                            context,
                            "Fill all fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        val issueId = database.push().key

                        if (issueId == null) {
                            Toast.makeText(
                                context,
                                "Failed to generate ID",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val issue = Issue(
                            title,
                            description,
                            location,
                            issueId,
                            currentUserId ?: ""   // ✅ ADDED
                        )

                        database.child(issueId)
                            .setValue(issue)
                            .addOnSuccessListener {

                                Toast.makeText(
                                    context,
                                    "Issue Submitted",
                                    Toast.LENGTH_SHORT
                                ).show()

                                title = ""
                                description = ""
                                location = ""
                            }
                            .addOnFailureListener { e ->

                                Toast.makeText(
                                    context,
                                    "Failed: ${e.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = beeGreen
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    "SUBMIT ISSUE",
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportIssueScreenPreview() {
    ReportIssueScreen(rememberNavController())
}