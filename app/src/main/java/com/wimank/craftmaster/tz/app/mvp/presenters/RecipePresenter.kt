package com.wimank.craftmaster.tz.app.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.mvp.views.RecipeView
import com.wimank.craftmaster.tz.app.room.entity.FavoritesEntity
import io.reactivex.Single
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
                        viewState.setRecipeAttr(it.first)
                        loadDevices(recipeAttr)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipe_loading_error)
                    })
        )
    }

    private fun loadDevices(recipeAttr: String) {
        unsubscribeOnDestroy(
            mRecipeManager
                .getDeviceFromDb(recipeAttr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showProgress(false)
                        viewState.showMessage(R.string.devices_load_successfully)

                        with(mRecipeManager.getDeviceName(it)) {
                            if (mRecipeManager.getDeviceName(it).isNotEmpty())
                                viewState.showDevice(this)
                        }

                        with(mRecipeManager.getMachine(it)) {
                            if (nameNotEmpty() || imageNotEmpty())
                                viewState.showMachine(this)
                        }
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.devices_load_error)
                    }
                ))
    }

    fun addToFavorite(favoritesEntity: FavoritesEntity) {
        unsubscribeOnDestroy(
            Single.fromCallable {
                mRecipeManager.addToFavorites(favoritesEntity)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        Log.d("FAVORITES", "onSuccess addToFavorite")
                    },
                    onError = {
                        Log.d("FAVORITES", "onError addToFavorite")
                    }
                ))
    }

    private fun switchFavoriteImage(value: Boolean) {
        if (value)
            viewState.favoriteItem(R.drawable.ic_favorite_true_24px)
        else
            viewState.favoriteItem(R.drawable.ic_favorite_false_24px)
    }

}
