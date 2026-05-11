package com.bright.beepower.models

data class User(
    val username: String = "",
    val email: String = "",
    val meterNumber: String = "",
    val uid: String = "",
    val role: String = "user",
    val balance: Int = 0
)
