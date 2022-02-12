package com.rapidcore.core.extension

import android.content.Context
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

fun randomUUID(): String = UUID.randomUUID().toString()

fun Bitmap.toFile(context: Context): File {
    val f = File(context.cacheDir, randomUUID() +".jpg")
    f.createNewFile()

    val byteStream = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.JPEG, 10, byteStream)
    val outputStream = FileOutputStream(f)
    outputStream.write(byteStream.toByteArray())
    outputStream.flush()
    outputStream.close()
    return f
}