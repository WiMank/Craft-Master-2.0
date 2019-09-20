package com.wimank.craftmaster.tz.main_screen.mvp.models

import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class MainGroupDataBaseManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun writeResponseInDb(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().insert(mainGroupEntity)
    }

    fun getMainGroup(): Flowable<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getMainGroup()
    }

    fun getJustMainGroup(): List<MainGroupEntity> {
        return craftMasterDataBase.mainGroupDao().getMainGroupTest()
    }

    fun deleteGroupEntity(mainGroupEntity: MainGroupEntity) {
        return craftMasterDataBase.mainGroupDao().delete(mainGroupEntity)
    }
}