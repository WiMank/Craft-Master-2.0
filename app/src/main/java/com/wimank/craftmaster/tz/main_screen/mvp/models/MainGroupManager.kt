package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItem
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class MainGroupManager(
    private val context: Context,
    private val mainGroupApi: MainGroupApi,
    private val mainGroupDataBaseManager: MainGroupDataBaseManager
) {

    fun checkDbVersions(groupListItem: GroupListItem){

    }

    fun getMainGroupFromDb(): Flowable<List<MainGroupEntity>> {
        return mainGroupDataBaseManager.getMainGroup()
    }

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

    fun getMainGroupImage(imageName: String): Single<ResponseBody> {
        return mainGroupApi.getGroupImage(imageName)
    }

    fun writeResponse(inputStream: InputStream, groupListItem: GroupListItem) {
        val parent = context.getExternalFilesDir(IMAGE_FOLDER_NAME)
        val targetImage = File(parent, "${groupListItem.groupImage}.png")
        FileOutputStream(targetImage).use { output ->
            BitmapFactory.decodeStream(inputStream.buffered()).run {
                compress(Bitmap.CompressFormat.PNG, 100, output)
            }
            output.flush()
        }
        mainGroupDataBaseManager.writeResponseInDb(
            MainGroupEntity(
                groupListItem.group,
                targetImage.path,
                groupListItem.endpoint,
                groupListItem.orderGroup,
                groupListItem.vers
            )
        )
    }
}