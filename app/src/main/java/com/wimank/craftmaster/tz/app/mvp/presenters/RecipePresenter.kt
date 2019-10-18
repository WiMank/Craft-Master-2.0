package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.mvp.views.RecipeView
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
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
                        viewState.showError(R.string.recipe_successfully_loaded)
                        viewState.fillRecipeImages(it.first)
                        viewState.fillCraftTable(it.second)
                        viewState.showLocalizedName(mRecipeManager.localizedName(it.first.recipeName))
                        viewState.showLocalizeDescription(mRecipeManager.localizeDescription(it.first.descriptionCraft))
                        viewState.showLocalizeLeftPar(mRecipeManager.localizeLeftPar(it.first.leftParameter))
                        viewState.showLocalizeLeftParText(mRecipeManager.localizeLeftParText(it.first.leftParameterText))
                        viewState.showLocalizeRightPar(mRecipeManager.localizeRightPar(it.first.rightParameter))
                        viewState.showLocalizeRightParText(mRecipeManager.localizeRightParText(it.first.rightParameterText))
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipe_loading_error)
                    })
        )
    }
}
