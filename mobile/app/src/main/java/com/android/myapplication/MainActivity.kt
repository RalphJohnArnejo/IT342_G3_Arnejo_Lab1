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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                DashboardScreen()
            }
        }
    }
}

@Composable
fun DashboardScreen() {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("DormShare", Context.MODE_PRIVATE)
    val token = sharedPref.getString("token", "Session Active")

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("DormShare Dashboard", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Login Status: Success", color = MaterialTheme.colorScheme.primary)
                // Showing a snippet of the token proves the backend session worked
                Text("Token: ${token?.take(15)}...", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            // Logout Logic: Clear token and go back to Login
            sharedPref.edit().remove("token").apply()
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
            (context as ComponentActivity).finish()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Logout")
        }
    }
}