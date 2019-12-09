package com.wimank.craftmaster.tz.app.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.wimank.craftmaster.tz.app.room.entity.DbVersionEntity
import io.reactivex.Single

@Dao
interface DbVersionDao : BaseDao<DbVersionEntity> {

    @Query("SELECT * FROM db_vers ORDER BY db_version DESC LIMIT 1")
    fun getDbVersFromDb(): Single<DbVersionEntity>

}
