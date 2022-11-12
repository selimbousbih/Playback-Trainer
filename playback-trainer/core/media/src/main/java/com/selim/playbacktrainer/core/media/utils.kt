package com.selim.playbacktrainer.core.media

import java.nio.ByteBuffer

// Convert data to float needed when using the pitch detector

private fun short2Float(sData: ShortArray): FloatArray {
    return sData.map { it.toFloat() }.toFloatArray()
}

private fun bytes2Float(bytes: ByteArray): FloatArray {
    val buffer = ByteBuffer.wrap(bytes)
    val arrSize = bytes.size
    val result = FloatArray(arrSize)
    for (i in 0 until arrSize) {
        result[i] = buffer.float
    }
    return result
}