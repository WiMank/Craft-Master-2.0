package com.wimank.craftmaster.tz.recipes_list.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.recipe_screen.room.DescriptionEntity
import com.wimank.craftmaster.tz.recipes_list.room.RecipesListItem

@StateStrategyType(AddToEndSingleStrategy::class)
interface RecipesListView : MvpView {

    fun initViews()

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)

    fun showProgress(visibilityFlag: Boolean)

    fun showRecipesList(list: List<RecipesListItem>)

}
