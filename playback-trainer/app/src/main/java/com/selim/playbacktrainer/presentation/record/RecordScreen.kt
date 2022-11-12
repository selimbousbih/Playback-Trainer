package com.selim.playbacktrainer.presentation.record

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.selim.playbacktrainer.presentation.navigation.NavigationAction
import com.selim.playbacktrainer.redux.AppState

@Composable
fun RecordScreen(
    state: AppState,
    navigateToAbout: NavigationAction = {},
    toggleRecording: () -> Unit = {},
    togglePlaying: () -> Unit = {},
) {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.width(120.dp),
                onClick = { toggleRecording() }) {
                Text(text = if (state.isRecording) "Stop" else "Record")
            }

            Button(
                modifier = Modifier.width(120.dp),
                onClick = { togglePlaying() }
            ) {
                Text(text = if (state.isPlaying) "Stop" else "Play")
            }

            Button(
                modifier = Modifier.width(120.dp),
                onClick = navigateToAbout
            ) {
                Text(text = "About!")
            }
        }
    }
}