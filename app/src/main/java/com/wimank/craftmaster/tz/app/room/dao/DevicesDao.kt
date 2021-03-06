package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.DeviceEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface DevicesDao : BaseDao<DeviceEntity> {

    @Query("SELECT * FROM manufacturing_devices")
    fun getDevices(): Single<List<DeviceEntity>>

    @Query("SELECT * FROM manufacturing_devices WHERE recipeAttr =:pAttr")
    fun getDeviceByName(pAttr: String): Maybe<DeviceEntity>

}
