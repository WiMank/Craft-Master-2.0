package com.wimank.craftmaster.tz.common.di

import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): CraftMasterDataBase {
        val rdc = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                val values = ContentValues()
                values.put("version_db", 0)
                db.insert("data_base_version", OnConflictStrategy.IGNORE, values)
            }
            override fun onOpen(db: SupportSQLiteDatabase) {

            }
        }

        return Room.databaseBuilder(
            context,
            CraftMasterDataBase::class.java,
            "craft_master_recipes_db.db"
        ).addCallback(rdc).build()
    }
}