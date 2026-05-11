package com.bright.beepower.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.bright.beepower.models.Product
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class ProductViewModel(
    private var navController: NavController,
    private var context: Context
) {

    private val database = FirebaseDatabase.getInstance().reference

    // ➕ ADD TOKEN PURCHASE
    fun addToken(
        name: String,
        amount: Double,
        units: Double,
        meterNumber: String
    ) {

        if (name.isBlank() || meterNumber.isBlank()) {
            Toast.makeText(context, "Fill all fields", Toast.LENGTH_LONG).show()
            return
        }

        val id = UUID.randomUUID().toString()

        val token = Product(
            id = id,
            name = name,
            amount = amount,
            units = units,
            meterNumber = meterNumber,
            timestamp = System.currentTimeMillis()
        )

        database.child("Tokens").child(id)
            .setValue(token)
            .addOnSuccessListener {
                Toast.makeText(context, "Token saved", Toast.LENGTH_SHORT).show()
                navController.navigate("dashboard")
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }
    }


    fun getTokens(onResult: (List<Product>) -> Unit) {

        database.child("Tokens")
            .get()
            .addOnSuccessListener { snapshot ->

                val list = mutableListOf<Product>()

                snapshot.children.forEach {
                    val token = it.getValue(Product::class.java)
                    token?.let { list.add(it) }
                }

                onResult(list)
            }
    }


    fun deleteToken(id: String) {

        database.child("Tokens").child(id)
            .removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }
    }
}