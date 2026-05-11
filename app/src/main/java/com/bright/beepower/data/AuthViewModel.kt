package com.bright.beepower.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(
    private val navController: NavController,
    private val context: Context
) {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    // ================= SIGNUP =================
    fun signup(
        username: String,
        email: String,
        meterNumber: String,
        password: String,
        confirmPassword: String
    ) {

        if (username.isBlank() || email.isBlank() || meterNumber.isBlank()
            || password.isBlank() || confirmPassword.isBlank()
        ) {
            Toast.makeText(context, "All fields are required", Toast.LENGTH_LONG).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email.trim(), password.trim())
            .addOnSuccessListener { result ->

                val uid = result.user?.uid ?: return@addOnSuccessListener

                val userMap = hashMapOf(
                    "username" to username,
                    "email" to email,
                    "meterNumber" to meterNumber,
                    "uid" to uid,
                    "role" to "user",
                    "balance" to 0
                )

                database.child("Users")
                    .child(uid)
                    .setValue(userMap)
                    .addOnSuccessListener {

                        // SESSION UPDATE (FIX)
                        UserSession.uid = uid
                        UserSession.username = username
                        UserSession.email = email
                        UserSession.meterNumber = meterNumber
                        UserSession.role = "user"
                        UserSession.balance = 0

                        Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate("dashboard")
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, it.message ?: "Database Error", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(context, it.message ?: "Signup Failed", Toast.LENGTH_SHORT).show()
            }
    }

    // ================= LOGIN =================
    fun login(email: String, password: String) {

        val cleanEmail = email.trim()
        val cleanPassword = password.trim()

        if (cleanEmail.isBlank() || cleanPassword.isBlank()) {
            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.signInWithEmailAndPassword(cleanEmail, cleanPassword)
            .addOnSuccessListener { result ->

                val uid = result.user?.uid ?: return@addOnSuccessListener

                database.child("Users")
                    .child(uid)
                    .get()
                    .addOnSuccessListener { snapshot ->

                        if (!snapshot.exists()) {
                            Toast.makeText(context, "User data not found", Toast.LENGTH_SHORT).show()
                            return@addOnSuccessListener
                        }

                        val role = snapshot.child("role").value?.toString() ?: "user"

                        // SESSION UPDATE (FIX)
                        UserSession.uid = uid
                        UserSession.username = snapshot.child("username").value?.toString()
                        UserSession.email = snapshot.child("email").value?.toString()
                        UserSession.meterNumber = snapshot.child("meterNumber").value?.toString()
                        UserSession.role = role
                        UserSession.balance = snapshot.child("balance").value?.toString()?.toIntOrNull() ?: 0

                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()

                        if (role == "admin") {
                            navController.navigate("admin_dashboard")
                        } else {
                            navController.navigate("dashboard")
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Database error: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener {
                Toast.makeText(context, it.message ?: "Login Failed", Toast.LENGTH_SHORT).show()
            }
    }

    fun logout() {
        mAuth.signOut()
        UserSession.clear()
        navController.navigate("login")
    }
}