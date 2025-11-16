package com.ottapp.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Guest User",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                ProfileMenuItem("Account Settings")
                Divider()
                ProfileMenuItem("Preferences")
                Divider()
                ProfileMenuItem("About")
                Divider()
                ProfileMenuItem("Logout")
            }
        }
    }
}

@Composable
private fun ProfileMenuItem(title: String) {
    TextButton(
        onClick = { },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
