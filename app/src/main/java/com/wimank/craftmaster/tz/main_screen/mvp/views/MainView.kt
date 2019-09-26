package com.wimank.craftmaster.tz.main_screen.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {

    fun showMessage(message: Int)

    fun showError(message: Int)

    fun showProgress(visibilityFlag : Boolean)

    fun showGroupList(list: List<MainGroupEntity>)

}