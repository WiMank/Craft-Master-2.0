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
    private val replaceList = mutableListOf<GroupListItem>()

    fun checkItemsVersion(pGroupList: List<GroupListItem>): List<GroupListItem> {
        val serverList = pGroupList.sortedWith(compareBy { it.orderGroup })
        val localList = mainGroupDataBaseManager.getJustMainGroup().sortedWith(compareBy { it.orderGroup })
        replaceList.clear()
        simpleCheckItems(serverList, localList)
        checkAddItems(serverList, localList)
        checkDeleteItems(serverList, localList)
        return replaceList
    }

    private fun simpleCheckItems(serverList: List<GroupListItem>, localList: List<MainGroupEntity>) {
        if (serverList.size == localList.size) {
            for (i in serverList.indices) {
                if (serverList[i].vers > localList[i].vers)
                    replaceList.add(serverList[i])
            }
        }
    }

    private fun checkAddItems(serverList: List<GroupListItem>, localList: List<MainGroupEntity>) {
        if (serverList.size > localList.size) {
            for (i in serverList.indices) {
                if (checkLocalArray(localList, serverList[i].group)) {
                    if (i < localList.size)
                        if (serverList[i].vers > localList[i].vers)
                            replaceList.add(serverList[i])
                } else
                    replaceList.add(serverList[i])
            }
        }
    }

    private fun checkDeleteItems(serverList: List<GroupListItem>, localList: List<MainGroupEntity>) {
        if (serverList.size < localList.size) {
            for (i in localList.indices) {
                if (checkServerArray(serverList, localList[i].group)) {
                    if (i < serverList.size)
                        if (serverList[i].vers > localList[i].vers)
                            replaceList.add(serverList[i])
                } else
                    mainGroupDataBaseManager.deleteGroupEntity(localList[i])
            }
        }
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