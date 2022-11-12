package com.selim.playbacktrainer

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.selim.playbacktrainer.media.MediaController
import com.selim.playbacktrainer.redux.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@SuppressLint("NewApi")
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mediaController: MediaController,
) : ViewModel() {
    private val store = createStore(reducer, AppState.INIT)

    var state by mutableStateOf(AppState.INIT)
        private set

    init {
        store.subscribe {
            state = store.state

            when (state) {
                AppState.IdleState -> {
                    stopPlaying()
                }

                AppState.PlayingState -> {
                    startPlaying()
                }

                AppState.RecordingState -> {
                    stopPlaying()
                    startRecording()
                }

                AppState.FinishRecordingState -> {
                    stopRecording()
                }
            }
        }
    }

    fun toggleRecording() {
        if (state.isRecording) {
            store.dispatch(Action.FinishRecord)
        } else {
            store.dispatch(Action.StartRecord)
        }
    }

    fun togglePlaying() {
        if (state.isPlaying) {
            store.dispatch(Action.StopPlay)
        } else {
            store.dispatch(Action.Play)
        }
    }

    private fun startRecording() {
        mediaController.startRecording()
    }

    private fun stopRecording() {
        mediaController.stopRecording()
    }

    private fun startPlaying() {
        mediaController.startPlaying {
            store.dispatch(Action.StopPlay)
        }
    }

    private fun stopPlaying() {
        mediaController.stopPlaying()
    }
}
