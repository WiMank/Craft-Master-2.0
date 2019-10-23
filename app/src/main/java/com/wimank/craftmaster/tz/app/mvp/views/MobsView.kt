package com.wimank.craftmaster.tz.app.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.room.entitys.MobsEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface MobsView : MvpView {

    fun iniViews(mobsEntity: MobsEntity)

    fun showMob(mobsEntity: MobsEntity)

    fun showMobName(name: String)

    fun showMobType(type: String)

    fun showMobDescription(description: String)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

}
