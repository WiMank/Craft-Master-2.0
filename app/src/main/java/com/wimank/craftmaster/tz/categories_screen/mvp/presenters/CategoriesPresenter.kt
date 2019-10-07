package com.wimank.craftmaster.tz.categories_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.categories_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.categories_screen.mvp.views.CategoriesView
import com.wimank.craftmaster.tz.common.mvp.BasePresenter


@InjectViewState
class CategoriesPresenter(private val categoriesManager: CategoriesManager) :
    BasePresenter<CategoriesView>() {

    fun loadMcCategories() {

    }
}