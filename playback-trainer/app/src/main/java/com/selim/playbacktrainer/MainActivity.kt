package com.selim.playbacktrainer

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.selim.playbacktrainer.presentation.navigation.PlaybackTrainerNavHost
import com.selim.playbacktrainer.presentation.ui.theme.PlaybackTrainerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlaybackTrainerTheme {
                SinglePermission(permission = Manifest.permission.RECORD_AUDIO)
                PlaybackTrainerNavHost()
            }
        }
    }
}
