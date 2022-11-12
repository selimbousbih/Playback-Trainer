package com.selim.playbacktrainer.media

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import com.selim.playbacktrainer.core.media.IWavFileRecorder
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

class MediaController
@Inject constructor(
    @ApplicationContext private val context: Context,
    private val wavRecorder: IWavFileRecorder
) {
    private val outputFilename = "${context.externalCacheDir?.absolutePath}/current_recording.wav"

    var isRecording = false

    private var mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .build()
        )
    }

    fun startRecording() {
        wavRecorder.startRecording(outputFilename)
        isRecording = true
    }

    fun stopRecording() {
        wavRecorder.stopRecording()
        isRecording = false
    }

    fun startPlaying(onComplete: () -> Unit = {}) {
        if (isRecording) {
            stopRecording()
        }

        if (mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
            return
        }

        mediaPlayer.reset()
        mediaPlayer.apply {
            try {
                setOnCompletionListener { onComplete() }
                setDataSource(outputFilename)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e("RECORDING CONTROLLER", "prepare() failed")
            }
        }
    }

    fun stopPlaying() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
    }
}