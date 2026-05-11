package com.bright.beepower.models

data class Product(
    val id: String = "",
    val name: String = "",          // e.g. "Token Purchase"
    val amount: Double = 0.0,       // KES
    val units: Double = 0.0,        // electricity units
    val meterNumber: String = "",   // who bought
    val timestamp: Long = 0L        // when
)