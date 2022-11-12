package com.selim.playbacktrainer.presentation.about

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.selim.playbacktrainer.presentation.navigation.NavigationAction

@Composable
fun AboutScreen(
    navigateToRecord: NavigationAction = {}
) {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Column {
            Text(
                text = "Hello Android!",
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = navigateToRecord
            ) {
                Text(text = "Back to recording screen.")
            }
        }
    }
}