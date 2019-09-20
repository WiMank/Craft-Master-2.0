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

    fun checkItemsVersion(pGroupList: List<GroupListItem>): List<GroupListItem> {
        val replaceList = mutableListOf<GroupListItem>()
        val sortedGroupList = pGroupList.sortedWith(compareBy { it.orderGroup })
        val sortedListEntity =
            mainGroupDataBaseManager.getJustMainGroup().sortedWith(compareBy { it.orderGroup })

        if (sortedGroupList.size == sortedListEntity.size) {
            for (i in sortedGroupList.indices) {
                if (sortedGroupList[i].vers > sortedListEntity[i].vers)
                    replaceList.add(sortedGroupList[i])
            }
        }

        if (sortedGroupList.size > sortedListEntity.size) {
            for (i in sortedGroupList.indices) {
                if (checkLocalArray(sortedListEntity, sortedGroupList[i].group)) {
                    if (i < sortedListEntity.size)
                        if (sortedGroupList[i].vers > sortedListEntity[i].vers)
                            replaceList.add(sortedGroupList[i])
                } else
                    replaceList.add(sortedGroupList[i])
            }
        }

        if (sortedGroupList.size < sortedListEntity.size) {
            for (i in sortedListEntity.indices) {
                if (checkServerArray(sortedGroupList, sortedListEntity[i].group)) {
                    if (i < sortedGroupList.size)
                        if (sortedGroupList[i].vers > sortedListEntity[i].vers)
                            replaceList.add(sortedGroupList[i])
                } else
                    mainGroupDataBaseManager.deleteGroupEntity(sortedListEntity[i])
            }
        }

        return replaceList
    }

    private fun checkLocalArray(list: List<MainGroupEntity>, targetItem: String): Boolean {
        return list.any { it.group == targetItem }
    }

    private fun checkServerArray(list: List<GroupListItem>, targetItem: String): Boolean {
        return list.any { it.group == targetItem }
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