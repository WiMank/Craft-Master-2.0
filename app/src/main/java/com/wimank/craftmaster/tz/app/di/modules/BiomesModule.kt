package com.wimank.craftmaster.tz.app.di.modules

import android.content.Context
import com.wimank.craftmaster.tz.app.di.scopes.BiomesScope
import com.wimank.craftmaster.tz.app.mvp.models.BiomeManager
import com.wimank.craftmaster.tz.app.mvp.presenters.BiomesPresenter
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class BiomesModule {

    @BiomesScope
    @Provides
    fun provideBiomesPresenter(biomeManager: BiomeManager): BiomesPresenter {
        return BiomesPresenter(biomeManager)
    }

    @BiomesScope
    @Provides
    fun provideBiomesManager(
        context: Context,
        craftMasterDataBase: CraftMasterDataBase
    ): BiomeManager {
        return BiomeManager(context, craftMasterDataBase)
    }
}