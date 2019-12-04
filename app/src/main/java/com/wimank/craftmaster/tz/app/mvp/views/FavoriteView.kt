package com.wimank.craftmaster.tz.app.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavoriteView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)

    fun showProgress(visibilityFlag: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showList(list: List<FavoriteEntity>)

    fun updateList(list: List<FavoriteEntity>)

    fun optionalTitleSetting(titleMod: String)
}
