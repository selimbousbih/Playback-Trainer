package com.selim.playbacktrainer.di

import com.selim.playbacktrainer.core.media.IWavFileRecorder
import com.selim.playbacktrainer.core.media.WavFileRecorder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MediaModule {

    @Provides
    fun provideWavFileRecorder(): IWavFileRecorder = WavFileRecorder()
}