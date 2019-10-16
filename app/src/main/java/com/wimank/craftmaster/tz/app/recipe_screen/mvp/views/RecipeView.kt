package com.wimank.craftmaster.tz.app.recipe_screen.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.recipe_screen.room.DescriptionEntity
import com.wimank.craftmaster.tz.app.recipe_screen.room.RecipeEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface RecipeView : MvpView {

    fun initViews()

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)

    fun showProgress(visibilityFlag: Boolean)

    fun fillRecipeImages(entity: DescriptionEntity)

    fun fillCraftTable(entity: RecipeEntity)

    fun showLocalizedName(name: String)

    fun showLocalizeDescription(desc: String)

    fun showLocalizeLeftPar(leftP: String)

    fun showLocalizeLeftParText(leftPText: String)

    fun showLocalizeRightPar(rightP: String)

    fun showLocalizeRightParText(rightPText: String)

}
