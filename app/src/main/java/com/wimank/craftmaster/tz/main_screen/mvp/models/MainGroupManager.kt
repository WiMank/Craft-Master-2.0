package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.DiffUtil
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
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

    fun diffItemsVersion(serverList: List<MainGroupEntity>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(
            MainGroupDiffCallback(
                serverList,
                mainGroupDataBaseManager.getMainGroup()
            )
        )
    }

    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>> {
        return mainGroupDataBaseManager.getFlowableMainGroup()
    }

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

    fun getMainGroupImage(imageName: String): Single<ResponseBody> {
        return mainGroupApi.getGroupImage(imageName)
    }

    fun writeResponse(inputStream: InputStream, mainGroupEntity: MainGroupEntity) {
        val parent = context.getExternalFilesDir(IMAGE_FOLDER_NAME)
        val targetImage = File(parent, "${mainGroupEntity.groupImage}.png")
        FileOutputStream(targetImage).use { output ->
            BitmapFactory.decodeStream(inputStream.buffered()).run {
                compress(Bitmap.CompressFormat.PNG, 100, output)
            }
            output.flush()
        }
        mainGroupDataBaseManager.writeResponseInDb(mainGroupEntity)
    }
}