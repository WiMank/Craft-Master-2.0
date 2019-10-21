package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import androidx.room.Room
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
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
