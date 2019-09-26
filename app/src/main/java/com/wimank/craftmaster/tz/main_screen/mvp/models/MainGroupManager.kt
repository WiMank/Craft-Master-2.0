package com.wimank.craftmaster.tz.main_screen.mvp.models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.DbVersEntity
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.DbVersResponse
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupsVersionResponse
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
    private val craftMasterDataBase: CraftMasterDataBase
) {

    fun getMainGroup(): Single<MainGroupResponse> {
        return mainGroupApi.getGroupList()
    }

    fun getMainGroupImage(imageName: String): Single<ResponseBody> {
        return mainGroupApi.getGroupImage(imageName)
    }

    fun getGroupsVersion(): Single<GroupsVersionResponse> {
        return mainGroupApi.getGroupsVersion()
    }

    fun getServerDbVersion(): Single<DbVersResponse> {
        return mainGroupApi.geServerDbVersion()
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
        writeResponseInDb(mainGroupEntity)
    }

    private fun writeResponseInDb(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().insert(mainGroupEntity)
    }

    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getFlowableMainGroupFromDb()
    }

    fun getDbVersionFromDb(): Single<DbVersEntity> {
        return craftMasterDataBase.dbVersDaoDao().getDbVersionFromDb()
    }

    fun updateDbVersionFromDb(serverDbVersion : Int, dbId: Int) {
        Log.d("TEST", "insertDbVersionFromDb() $serverDbVersion")
        return craftMasterDataBase.dbVersDaoDao().update(serverDbVersion, dbId)
    }

    fun deleteMainGroupsFromDb() {
        craftMasterDataBase.mainGroupDao().delete()
    }
}