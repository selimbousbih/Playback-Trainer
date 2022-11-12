package com.selim.playbacktrainer.presentation.navigation

sealed class Routes(val route: String) {
    object About: Routes("about")
    object Record: Routes("record")
}
