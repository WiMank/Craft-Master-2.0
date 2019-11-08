package com.wimank.craftmaster.tz.app.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.room.entitys.AchievementEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface AchievementView : MvpView {

    fun showAchievements(list: List<AchievementEntity>)

}
