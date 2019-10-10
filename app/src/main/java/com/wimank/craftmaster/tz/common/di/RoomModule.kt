package com.wimank.craftmaster.tz.common.di

import android.content.Context
import androidx.room.Room
import com.wimank.craftmaster.tz.common.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): CraftMasterDataBase {
        return Room.databaseBuilder(
            context,
            CraftMasterDataBase::class.java,
            "craft_master_recipes_db.db"
        ).build()
    }
}
