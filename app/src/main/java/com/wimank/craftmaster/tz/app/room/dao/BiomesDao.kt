package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.BiomeListItem
import com.wimank.craftmaster.tz.app.room.entity.BiomesEntity
import io.reactivex.Single

@Dao
interface BiomesDao : BaseDao<BiomesEntity> {

    @Query("SELECT biome_image AS image, biome_name AS name FROM biomes ORDER BY biome_name ASC")
    fun getBiomesList(): Single<List<BiomeListItem>>

    @Query("SELECT* FROM biomes ORDER BY biome_name ASC")
    fun getAllBiomes(): Single<List<BiomesEntity>>

}
