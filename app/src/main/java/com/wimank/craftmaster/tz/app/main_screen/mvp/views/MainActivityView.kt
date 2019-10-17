package com.wimank.craftmaster.tz.app.main_screen.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.categories_screen.room.CategoryEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainActivityView : MvpView {

    fun openBlocksandItemsCategory(categoryEntity: CategoryEntity)

    fun openMobsCategory()

    fun openBiomesCategory()

    fun openAchievementsCategory(categoryEntity: CategoryEntity)

    fun openPotionsCategory()

    fun openComandsCategory()

}