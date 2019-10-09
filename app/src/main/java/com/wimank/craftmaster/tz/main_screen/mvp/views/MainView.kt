package com.wimank.craftmaster.tz.main_screen.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.main_screen.room.MainGroupEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun initViews()

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)

    fun showProgress(visibilityFlag : Boolean)

    fun showGroupList(list: List<MainGroupEntity>)

}