package com.wimank.craftmaster.tz.app.di.modules

import com.wimank.craftmaster.tz.app.di.scopes.SearchScope
import com.wimank.craftmaster.tz.app.mvp.models.SearchManager
import com.wimank.craftmaster.tz.app.mvp.presenters.SearchPresenter
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @SearchScope
    fun provideSearchPresenter(searchManager: SearchManager): SearchPresenter {
        return SearchPresenter(searchManager)
    }

    @Provides
    @SearchScope
    fun provideSearchManager(craftMasterDataBase: CraftMasterDataBase): SearchManager {
        return SearchManager(craftMasterDataBase)
    }

}
