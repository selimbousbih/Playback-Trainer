package com.selim.playbacktrainer.redux


sealed class AppState(
    val isRecording: Boolean,
    val isPlaying: Boolean,
) {
    object IdleState: AppState(isRecording = false, isPlaying = false)
    object RecordingState: AppState(isRecording = true, isPlaying = false)
    object FinishRecordingState: AppState(isRecording = false, isPlaying = false)
    object PlayingState: AppState(isRecording = false, isPlaying = true)

    companion object {
        val INIT: AppState = IdleState
    }
}