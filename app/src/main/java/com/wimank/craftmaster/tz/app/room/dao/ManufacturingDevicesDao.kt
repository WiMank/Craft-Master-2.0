package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entitys.ManufacturingDevicesEntity
import io.reactivex.Single

@Dao
interface ManufacturingDevicesDao : BaseDao<ManufacturingDevicesEntity> {

    @Query("SELECT * FROM manufacturing_devices")
    fun getManufacturingDevices(): Single<List<ManufacturingDevicesEntity>>

    @Query("SELECT * FROM manufacturing_devices WHERE recipeAttr =:pAttr")
    fun getManufacturingDevicesByName(pAttr: String): ManufacturingDevicesEntity

}
