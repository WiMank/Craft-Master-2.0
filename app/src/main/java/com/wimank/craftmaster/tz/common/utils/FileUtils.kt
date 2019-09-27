package com.wimank.craftmaster.tz.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

fun writeImage(context: Context, inputStream: InputStream, imageName: String) {
    val parent = context.getExternalFilesDir(IMAGE_FOLDER_NAME)
    val targetImage = File(parent, "${imageName}.png")
    FileOutputStream(targetImage).use { output ->
        BitmapFactory.decodeStream(inputStream.buffered()).run {
            compress(Bitmap.CompressFormat.PNG, 100, output)
        }
        output.flush()
    }
}