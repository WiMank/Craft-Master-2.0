package com.wimank.craftmaster.tz.main_screen.mvp.models

import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import io.reactivex.Single

class MainGroupDataBaseManager(private val craftMasterDataBase: CraftMasterDataBase) {

    fun writeResponseInDb(mainGroupEntity: MainGroupEntity) {
        craftMasterDataBase.mainGroupDao().insert(mainGroupEntity)
    }

    fun getMainGroupFromDb(): Single<List<MainGroupEntity>> {
        return craftMasterDataBase.mainGroupDao().getMainGroupFromDb()
    }

    fun deleteGroupEntity(mainGroupEntity: MainGroupEntity) {
        return craftMasterDataBase.mainGroupDao().delete(mainGroupEntity)
    }
}