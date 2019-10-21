package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.BC_VALUE
import com.wimank.craftmaster.tz.app.mvp.common.FR_VALUE
import com.wimank.craftmaster.tz.app.mvp.common.IC_VALUE
import com.wimank.craftmaster.tz.app.mvp.common.MC_VALUE
import com.wimank.craftmaster.tz.app.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.mvp.views.RecipeView
import com.wimank.craftmaster.tz.app.room.RecipesListItem
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

    fun chooseRecipeModification(recipesListItem: RecipesListItem) {
        when (recipesListItem.modification) {
            MC_VALUE -> lodRecipeAndDescription(recipesListItem)

            IC_VALUE -> {
            }

            BC_VALUE -> {
            }

            FR_VALUE -> {
            }
        }
    }


    private fun lodRecipeAndDescription(recipesListItem: RecipesListItem) {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            mRecipeManager
                .getDescriptionFromDb(recipesListItem.recipeAttr ?: return)
                .zipWith(mRecipeManager.getRecipeFromDb(recipesListItem.recipeAttr))
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
