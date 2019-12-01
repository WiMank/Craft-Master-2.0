package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.FavoriteScope
import com.wimank.craftmaster.tz.app.mvp.models.FavoriteManager
import com.wimank.craftmaster.tz.app.mvp.presenters.FavoritePresenter
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class FavoriteModule {

    @Provides
    @FavoriteScope
    fun provideFavoritePresenter(favoriteManager: FavoriteManager): FavoritePresenter {
        return FavoritePresenter(favoriteManager)
    }

    @Provides
    @FavoriteScope
    fun provideFavoriteManager(craftMasterDataBase: CraftMasterDataBase): FavoriteManager {
        return FavoriteManager(craftMasterDataBase)
    }

}
