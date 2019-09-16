package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import com.wimank.craftmaster.tz.common.utils.MAIN_URL
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItem
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import java.io.File
import java.io.FileOutputStream
import android.graphics.Bitmap
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.Glide
import com.wimank.craftmaster.tz.common.di.GlideApp


private var RELATIVE_LOCATION = Environment.DIRECTORY_PICTURES + File.pathSeparator + ".CM2I"

class MainGroupModel(private val context: Context, private val mainGroupApi: MainGroupApi) {

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

    fun writeResponse(mainGroupResponse: MainGroupResponse) {
        mainGroupResponse.groupList.forEach {
            writeGroupListItem(it)
        }
    }

    private fun writeGroupListItem(groupListItem: GroupListItem) {
       /* val image = "$MAIN_URL${groupListItem.groupImage}"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, groupListItem.groupImage)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    "DCIM${File.separator}$RELATIVE_LOCATION"
                )
            }
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            val outputStream = imageUri?.let { resolver.openOutputStream(it) }
            outputStream.use { output ->
                output?.write(imageByteArray)
                output?.flush()
            }
        } else {
            val file =
                File("$RELATIVE_LOCATION${File.separator + groupListItem.groupImage + ".png"}")
            val outputStream = FileOutputStream(file)
            outputStream.use { output ->
                output.write(imageByteArray)
                output.flush()
            }
        }*/
    }

    private fun insertGroupListItem() {


    }
}