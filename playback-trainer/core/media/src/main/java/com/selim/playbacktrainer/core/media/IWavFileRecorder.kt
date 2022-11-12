package com.selim.playbacktrainer.core.media

interface IWavFileRecorder {
    fun startRecording(path: String)
    fun stopRecording()
}