package com.wimank.craftmaster.tz.app.categories_screen.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.categories_screen.room.CategoryEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoriesView : MvpView {

    fun initViews()

    fun showCategoryList(list: List<CategoryEntity>)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: Int)

    fun showProgress(visibilityFlag: Boolean)
}
