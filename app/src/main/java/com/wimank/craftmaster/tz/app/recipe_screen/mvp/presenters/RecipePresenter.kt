package com.wimank.craftmaster.tz.app.recipe_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.views.RecipeView
import com.wimank.craftmaster.tz.common.mvp.BasePresenter

@InjectViewState
class RecipePresenter(private val mRecipeManager: RecipeManager) : BasePresenter<RecipeView>() {

}
