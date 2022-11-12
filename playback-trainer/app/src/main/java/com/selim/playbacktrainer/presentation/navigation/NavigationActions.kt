package com.selim.playbacktrainer.presentation.navigation

import androidx.navigation.NavHostController

typealias NavigationAction = () -> Unit

class NavigationActions(navController: NavHostController) {

    val navigateToAbout: NavigationAction = {
        navController.navigate(Routes.About.route) {
            popUpTo(Routes.About.route) {
                inclusive = true
            }
        }
    }

    val navigateToRecord: NavigationAction = {
        navController.navigate(Routes.Record.route) {
            popUpTo(Routes.Record.route) {
                inclusive = true
            }
        }
    }
}
