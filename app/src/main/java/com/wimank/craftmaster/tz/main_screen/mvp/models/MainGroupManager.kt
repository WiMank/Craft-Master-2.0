package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.common.utils.MAIN_URL
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItem
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Single
import java.io.File
import java.io.FileOutputStream

class MainGroupManager(
    private val context: Context,
    private val mainGroupApi: MainGroupApi,
    private val mainGroupDataBaseManager: MainGroupDataBaseManager
) {

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

    fun writeResponse(mainGroupResponse: MainGroupResponse) {
        mainGroupResponse.groupList.forEach {
            downloadImage(it)
        }
    }

    private fun downloadImage(groupListItem: GroupListItem) {
        Log.i("TEST", "downloadImage: ${MAIN_URL + groupListItem.groupImage}")
        GlideApp.with(context)
            .asBitmap()
            .load(MAIN_URL + groupListItem.groupImage)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    writeImage(resource, groupListItem)
                }
            })
    }

    private fun writeImage(bitmap: Bitmap, groupListItem: GroupListItem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, groupListItem.groupImage)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + IMAGE_FOLDER_NAME
                )
            }
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

            val output = imageUri?.let { resolver.openOutputStream(it) }
            output.use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                it?.flush()
            }

            insertGroupListItem(groupListItem, imageUri.toString())

        } else {
            val relativeLocation =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + File.pathSeparator + IMAGE_FOLDER_NAME
            val imagesFolder = File(relativeLocation)
            if (!imagesFolder.exists()) imagesFolder.mkdir()
            val image = File(imagesFolder, "${groupListItem.groupImage}.png")
            FileOutputStream(image).use {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                it.flush()
            }

            insertGroupListItem(groupListItem, image.path)
        }
    }

    private fun insertGroupListItem(groupListItem: GroupListItem, imagePath: String) {
        mainGroupDataBaseManager.writeResponseInDb(
            MainGroupEntity(
                groupListItem.group,
                imagePath,
                groupListItem.endpoint,
                groupListItem.orderGroup
            )
        )
    }
}