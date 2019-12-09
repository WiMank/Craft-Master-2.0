package com.wimank.craftmaster.tz.app.di.modules

import android.content.ContentValues
import android.content.Context
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
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
                val contentValues = ContentValues()
                contentValues.put("db_version", 0)
                db.insert("db_vers", OnConflictStrategy.IGNORE, contentValues)
            }
        }

        return Room.databaseBuilder(
            context,
            CraftMasterDataBase::class.java,
            "craft_master_recipes_db.db"

        ).addCallback(rdc).build()
    }
}
