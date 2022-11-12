package com.selim.playbacktrainer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.selim.playbacktrainer.MainViewModel
import com.selim.playbacktrainer.presentation.about.AboutScreen
import com.selim.playbacktrainer.presentation.record.RecordScreen
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PlaybackTrainerNavHost() {
    val navController = rememberNavController()

    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }

    NavHost(navController = navController, startDestination = Routes.Record.route) {
        composable(Routes.Record.route) {
            val viewModel: MainViewModel = hiltViewModel()

            RecordScreen(
                state = viewModel.state,
                togglePlaying = viewModel::togglePlaying,
                toggleRecording = viewModel::toggleRecording,
                navigateToAbout = navigationActions.navigateToAbout
            )
        }

        composable(Routes.About.route) {
            AboutScreen(navigateToRecord = navigationActions.navigateToRecord)
        }
    }
}
