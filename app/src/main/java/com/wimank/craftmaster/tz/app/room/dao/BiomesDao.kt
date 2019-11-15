package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.BiomesEntity
import io.reactivex.Single

@Dao
interface BiomesDao : BaseDao<BiomesEntity> {

    @Query("SELECT * FROM biomes ORDER BY biome_name ASC")
    fun getBiomes(): Single<List<BiomesEntity>>

    @Query("SELECT * FROM biomes WHERE biome_image =:pBiome ORDER BY biome_name ASC")
    fun getBiome(pBiome: String): Single<BiomesEntity>

}
