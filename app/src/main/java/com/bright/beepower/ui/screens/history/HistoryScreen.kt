package com.bright.beepower.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {

    // 🎨 Colors (UPDATED THEME)
    val beeYellow = Color(0xFFF4C430)
    val lightGrey = Color(0xFFE0E0E0)
    val beeGreen = Color(0xFF1B5E20)

    // Mock Data
    val transactions = listOf(
        "KES 500 - 22.4 units",
        "KES 1000 - 45.1 units",
        "KES 200 - 8.9 units"
    )

    Scaffold(
        containerColor = Color.Transparent, // allow gradient background
        topBar = {
            TopAppBar(
                title = { Text("Transaction History", color = beeGreen) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = beeGreen)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = lightGrey
                )
            )
        }
    ) { padding ->

        // 🌈 BACKGROUND (Yellow + Light Grey)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(beeYellow, lightGrey)
                    )
                )
                .padding(padding)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(transactions) { tx ->
                    ListItem(
                        headlineContent = { Text(tx, color = beeGreen) },
                        supportingContent = { Text("Date: May 4, 2026") },
                        trailingContent = { Text("Success", color = beeGreen) }
                    )
                    Divider(color = beeGreen.copy(alpha = 0.3f))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(rememberNavController())
}