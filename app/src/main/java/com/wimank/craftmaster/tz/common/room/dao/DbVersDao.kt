package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wimank.craftmaster.tz.common.room.entities.DbVersEntity
import io.reactivex.Single

@Dao
interface DbVersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dbVersEntity: DbVersEntity)

    @Query("UPDATE data_base_version SET version_db = :serverDbVersion WHERE db_id = :dbId ")
    fun update(serverDbVersion: Int, dbId: Int)

    @Query("SELECT * FROM data_base_version")
    fun getDbVersionFromDb(): Single<DbVersEntity>

    @Query("DELETE FROM data_base_version")
    fun deleteMainGroups()
}