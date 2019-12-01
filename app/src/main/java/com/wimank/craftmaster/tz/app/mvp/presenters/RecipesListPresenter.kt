package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.*
import com.wimank.craftmaster.tz.app.mvp.models.RecipesListManager
import com.wimank.craftmaster.tz.app.mvp.views.RecipesListView
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class RecipesListPresenter(private val mRecipesListManager: RecipesListManager) :
    BasePresenter<RecipesListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initViews()
    }

    fun chooseModification(section: String) {
        when (section) {
            MC_VALUE -> loadRecipesList(MC_VALUE)
            IC_VALUE -> loadRecipesList(IC_VALUE)
            BC_VALUE -> loadRecipesList(BC_VALUE)
            FR_VALUE -> loadRecipesList(FR_VALUE)
            MOBS_VALUE -> loadMobsList()
            BIOMES_VALUE -> loadBiomesList()
        }
    }

    private fun loadRecipesList(modification: String) {
        viewState.optionalTitleSetting(modification)
        unsubscribeOnDestroy(
            mRecipesListManager
                .getRecipesList(modification)
                .flatMap {
                    Single.just((it.map { item ->
                        RecipesListItem(
                            item.recipeName,
                            item.recipeImageName,
                            item.recipeAttr,
                            modification
                        )
                    }))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showList(it)
                        viewState.showProgress(false)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipes_list_loaded_error)
                    })
        )
    }

    private fun loadMobsList() {
        viewState.optionalTitleSetting(MOBS_VALUE)
        unsubscribeOnDestroy(
            mRecipesListManager
                .getMobsList()
                .flatMap {
                    Single.just((it.map { item ->
                        RecipesListItem(
                            item.mobName,
                            item.mobIcon,
                            item.mobIcon,
                            MOBS_VALUE
                        )
                    }))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showList(it)
                        viewState.showProgress(false)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.mobs_error_loaded)
                    })
        )
    }

    private fun loadBiomesList() {
        viewState.optionalTitleSetting(BIOMES_VALUE)
        unsubscribeOnDestroy(
            mRecipesListManager
                .getBiomesList()
                .flatMap {
                    Single.just((it.map { item ->
                        RecipesListItem(
                            item.biomeName,
                            item.biomeImage,
                            item.biomeImage,
                            BIOMES_VALUE
                        )
                    }))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showList(it)
                        viewState.showProgress(false)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.biomes_load_successfully)
                    })
        )
    }

}
