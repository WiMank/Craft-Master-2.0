package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.mvp.views.RecipeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RecipePresenter(private val mRecipeManager: RecipeManager) : BasePresenter<RecipeView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initViews()
    }

    fun lodRecipeAndDescription(recipeAttr: String) {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            mRecipeManager
                .getDescriptionFromDb(recipeAttr)
                .zipWith(mRecipeManager.getRecipeFromDb(recipeAttr))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showProgress(false)
                        viewState.initTableListeners(it.second)
                        viewState.showError(R.string.recipe_successfully_loaded)
                        viewState.fillRecipeImages(it.first)
                        viewState.fillCraftTable(it.second)
                        viewState.showLocalizedName(mRecipeManager.localizeString(it.first.recipeName))
                        viewState.showLocalizeDescription(mRecipeManager.localizeString(it.first.descriptionCraft))
                        viewState.showLocalizeLeftPar(mRecipeManager.localizeString(it.first.leftParameter))
                        viewState.showLocalizeLeftParText(mRecipeManager.localizeString(it.first.leftParameterText))
                        viewState.showLocalizeRightPar(mRecipeManager.localizeString(it.first.rightParameter))
                        viewState.showLocalizeRightParText(mRecipeManager.localizeString(it.first.rightParameterText))
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipe_loading_error)
                    })
        )
    }
}