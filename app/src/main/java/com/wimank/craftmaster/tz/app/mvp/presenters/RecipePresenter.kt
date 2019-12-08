package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.RecipeManager
import com.wimank.craftmaster.tz.app.mvp.views.RecipeView
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
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
                        viewState.fillRecipeImages(it.first)
                        viewState.showLocalizedName(mRecipeManager.localizeString(it.first.recipeName))
                        viewState.showLocalizeDescription(mRecipeManager.localizeString(it.first.descriptionCraft))
                        viewState.showLocalizeLeftPar(mRecipeManager.localizeString(it.first.leftParameter))
                        viewState.showLocalizeLeftParText(mRecipeManager.localizeString(it.first.leftParameterText))
                        viewState.showLocalizeRightPar(mRecipeManager.localizeString(it.first.rightParameter))
                        viewState.showLocalizeRightParText(mRecipeManager.localizeString(it.first.rightParameterText))
                        viewState.setRecipeAttr(it.first)
                        loadDevices(recipeAttr)
                        loadAddInfo(recipeAttr)

                        if (mRecipeManager.checkCraftTableFilling(it.second))
                            viewState.hideCraftTable()
                        else
                            viewState.fillCraftTable(it.second)

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
                    },
                    onComplete = {
                        viewState.showDevice(
                            mRecipeManager.getString(R.string.recipe_craft_text)
                        )
                    }
                ))
    }

    private fun loadAddInfo(recipeAttr: String) {
        unsubscribeOnDestroy(
            mRecipeManager
                .getAddInfoFromDb(recipeAttr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showProgress(false)

                        viewState.showLocalizeLeftPar(mRecipeManager.localizeString(it.leftPr))
                        viewState.showLocalizeLeftParText((mRecipeManager.getAddInfoText(it)))


                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.devices_load_error)
                    },
                    onComplete = {
                        viewState.showDevice(
                            mRecipeManager.getString(R.string.recipe_craft_text)
                        )
                    }
                ))
    }

    fun addOrDelete(favoriteEntity: FavoriteEntity) {
        unsubscribeOnDestroy(
            Single.fromCallable { mRecipeManager.checkFavorite(favoriteEntity.fRecipeAttr) }
                .subscribeOn(Schedulers.io())
                .subscribeBy {
                    if (!it)
                        addToFavorite(favoriteEntity)
                    else
                        deleteFromFavorites(favoriteEntity)
                })
    }

    private fun addToFavorite(favoriteEntity: FavoriteEntity) {
        unsubscribeOnDestroy(
            Single.fromCallable { mRecipeManager.addToFavorites(favoriteEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { switchFavoriteImage(true) })
    }

    private fun deleteFromFavorites(favoriteEntity: FavoriteEntity) {
        unsubscribeOnDestroy(
            Single.fromCallable {
                mRecipeManager.deleteFromFavorites(favoriteEntity)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { switchFavoriteImage(false) })
    }

    fun checkFavorite(recipeAttr: String) {
        unsubscribeOnDestroy(
            Single.fromCallable { mRecipeManager.checkFavorite(recipeAttr) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { switchFavoriteImage(it) })
    }

    private fun switchFavoriteImage(value: Boolean) {
        if (value)
            viewState.favoriteItem(R.drawable.ic_favorite_true_24px)
        else
            viewState.favoriteItem(R.drawable.ic_favorite_false_24px)
    }

}