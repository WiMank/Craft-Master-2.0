package com.wimank.craftmaster.tz.common.room.dao

import androidx.room.*
import com.wimank.craftmaster.tz.common.room.entities.DbVersEntity
import com.wimank.craftmaster.tz.common.room.entities.GroupsVersionEntity
import io.reactivex.Single

@Dao
interface GroupsVersionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(groupsVersionEntity: GroupsVersionEntity)

    @Query("SELECT * FROM groups_version")
    fun getGroupsVersionFromDb(): Single<List<GroupsVersionEntity>>

    @Delete
    fun delete(groupsVersionEntity: GroupsVersionEntity)

}