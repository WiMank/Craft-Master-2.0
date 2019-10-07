package com.wimank.craftmaster.tz.categories_screen.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface CategoriesView : MvpView {

    fun showCategoryList(list: List<CategoryEntity>)

}