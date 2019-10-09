package com.wimank.craftmaster.tz.categories_screen.mvp.views

import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.common.mvp.BaseView

interface CategoriesView : BaseView {

    fun initViews()

    fun showCategoryList(list: List<CategoryEntity>)

    override fun showMessage(message: Int)

    override fun showError(message: Int)

    override fun showProgress(visibilityFlag: Boolean)
}