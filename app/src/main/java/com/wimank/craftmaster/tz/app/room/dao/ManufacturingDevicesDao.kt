package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entitys.ManufacturingDevicesEntity

interface ManufacturingDevicesDao : BaseDao<ManufacturingDevicesEntity> {

    @Query("SELECT * FROM manufacturing_devices WHERE recipeAttr =:pAttr")
    fun getManufacturingDevices(pAttr: String): ManufacturingDevicesEntity

}
