package com.wimank.craftmaster.tz.main_screen.mvp.models

import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import io.reactivex.Flowable

class MainGroupDataBaseManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun writeResponseInDb(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().insert(mainGroupEntity)
    }

    fun getFlowableMainGroup(): Flowable<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getFlowableMainGroup()
    }

    fun getMainGroup(): List<MainGroupEntity> {
        return craftMasterDataBase.mainGroupDao().getMainGroup()
    }

    fun deleteGroupEntity(mainGroupEntity: MainGroupEntity) {
        return craftMasterDataBase.mainGroupDao().delete(mainGroupEntity)
    }
}