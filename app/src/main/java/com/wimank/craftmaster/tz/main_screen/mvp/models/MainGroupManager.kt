package com.wimank.craftmaster.tz.main_screen.mvp.models

import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.DbVersEntity
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupApi
import com.wimank.craftmaster.tz.main_screen.rest.response.DbVersResponse
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupsVersionResponse
import com.wimank.craftmaster.tz.main_screen.rest.response.MainGroupResponse
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.ResponseBody


class MainGroupManager(
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

    fun writeResponseInDb(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().insert(mainGroupEntity)
    }

    fun getFlowableMainGroupFromDb(): Flowable<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getFlowableMainGroupFromDb()
    }

    fun getDbVersionFromDb(): Single<DbVersEntity> {
        return craftMasterDataBase.dbVersDaoDao().getDbVersionFromDb()
    }

    fun updateDbVersionFromDb(serverDbVersion : Int, dbId: Int) {
        return craftMasterDataBase.dbVersDaoDao().update(serverDbVersion, dbId)
    }

    fun deleteMainGroupsFromDb() {
        craftMasterDataBase.mainGroupDao().delete()
    }
}