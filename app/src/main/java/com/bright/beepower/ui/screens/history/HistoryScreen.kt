package com.bright.beepower.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {
    // Mock Data - In a real app, this comes from Firebase
    val transactions = listOf("KES 500 - 22.4 units", "KES 1000 - 45.1 units", "KES 200 - 8.9 units")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transaction History") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
            items(transactions) { tx ->
                ListItem(
                    headlineContent = { Text(tx) },
                    supportingContent = { Text("Date: May 4, 2026") },
                    trailingContent = { Text("Success", color = Color(0xFF1B5E20)) }
                )
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview(){

    HistoryScreen(rememberNavController())


}