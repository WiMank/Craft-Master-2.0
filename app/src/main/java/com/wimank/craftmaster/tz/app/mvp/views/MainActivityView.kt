package com.wimank.craftmaster.tz.app.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainActivityView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)

    fun showProgress(visibilityFlag: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun showRecipesList(section: String, iconifiedSV: Boolean = true)

    @StateStrategyType(SkipStrategy::class)
    fun showRecipeSection(item: String)

    @StateStrategyType(SkipStrategy::class)
    fun showRecipeSectionFromFav(item: String)

    @StateStrategyType(SkipStrategy::class)
    fun showMobsSection(mob: String)

    @StateStrategyType(SkipStrategy::class)
    fun showAchievementsSection()

    @StateStrategyType(SkipStrategy::class)
    fun showBrewingSection()

    @StateStrategyType(SkipStrategy::class)
    fun showBiomesSection(biome: String)

    @StateStrategyType(SkipStrategy::class)
    fun showFavoriteSection()

}
