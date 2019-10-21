package com.wimank.craftmaster.tz.app.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ImageUtils(private val context: Context) {

    fun writeImage(inputStream: InputStream, imageName: String) {
        val parent = context.getExternalFilesDir(IMAGE_FOLDER_NAME)
        val targetImage = File(parent, "${imageName}.png")
        if (!targetImage.exists())
            FileOutputStream(targetImage).use { output ->
                BitmapFactory.decodeStream(inputStream.buffered()).run {
                    compress(Bitmap.CompressFormat.PNG, 100, output)
                }
                output.flush()
            }
    }

    fun checkImageExist(imageName: String): Boolean {
        val parent = context.getExternalFilesDir(IMAGE_FOLDER_NAME)
        val targetImage = File(parent, "${imageName}.png")
        return targetImage.exists()
    }
}
