package com.android.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // REQUIREMENT: Dashboard/Profile screen (protected)
        // Check if a token exists in SharedPreferences
        val sharedPref = getSharedPreferences("DormShare", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", null)

        if (token == null) {
            // Redirect to Login if no token is found
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        setContent {
            MaterialTheme {
                DashboardScreen(onLogout = {
                    // REQUIREMENT: Logout functionality
                    // Clear the token and redirect back to Login
                    sharedPref.edit().clear().apply()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                })
            }
        }
    }
}

@Composable
fun DashboardScreen(onLogout: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to DormShare Dashboard", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Button to trigger the logout logic
        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}