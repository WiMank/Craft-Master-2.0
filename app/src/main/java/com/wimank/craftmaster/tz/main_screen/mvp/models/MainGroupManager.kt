package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream


class MainGroupManager(
    private val context: Context,
    private val mainGroupApi: MainGroupApi,
    private val mainGroupDataBaseManager: MainGroupDataBaseManager
) {

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

    fun getMainGroupImage(imageName: String): Single<ResponseBody> {
        return mainGroupApi.getGroupImage(imageName)
    }

    fun writeResponse(mainGroupResponse: MainGroupResponse) {

    }

    fun prepareWriteImage(inputStream: InputStream, imageName: String) {
        val parent = context.getExternalFilesDir(IMAGE_FOLDER_NAME)
        val targetImage = File(parent, "$imageName.png")
        writeImage(inputStream, FileOutputStream(targetImage))
    }

    private fun writeImage(inputStream: InputStream, outputStream: OutputStream) {
        outputStream.use { output ->
            BitmapFactory.decodeStream(inputStream.buffered()).run {
                compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
            output.flush()
        }
    }
}