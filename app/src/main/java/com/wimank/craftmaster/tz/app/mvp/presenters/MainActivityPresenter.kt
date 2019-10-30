package com.wimank.craftmaster.tz.app.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.*
import com.wimank.craftmaster.tz.app.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.mvp.models.NetManager
import com.wimank.craftmaster.tz.app.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.app.rest.responses.MobsResponse
import com.wimank.craftmaster.tz.app.rest.responses.RecipeResponse
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.MobsEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MainActivityPresenter(
    private val mDataManager: DataManager,
    private val mNetManager: NetManager
) : BasePresenter<MainActivityView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateData()
    }

    private fun updateData() {
        viewState.showProgress(true)
        if (mNetManager.isConnectedToNetwork())
            loadRecipes()
        else
            viewState.showMessage(R.string.offline_mode)
    }

    fun choseSection(section: String, item: String) {
        when (section) {
            MC_VALUE -> viewState.showBlockAndItemsSection(item)
            IC_VALUE -> viewState.showBlockAndItemsSection(item)
            BC_VALUE -> viewState.showBlockAndItemsSection(item)
            FR_VALUE -> viewState.showBlockAndItemsSection(item)
            MOBS_VALUE -> viewState.showMobsSection(item)
        }
    }

    private fun loadRecipes() {
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getRecipes(),
                mDataManager.getRecipesFromDb(),
                mDataManager.getDescriptionFromDb(),
                Function3 { servRecipes: RecipeResponse,
                            recipeEntity: List<RecipeEntity>,
                            descriptionEntity: List<DescriptionEntity> ->
                    if (servRecipes.success.isSuccess()) {
                        mDataManager.containsData(servRecipes.recipesList, recipeEntity)
                        mDataManager.containsData(servRecipes.descriptionList, descriptionEntity)
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showMessage(R.string.recipe_loading_completed)
                        viewState.showProgress(false)
                        loadMobs()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipes_list_loading_error)
                        Log.e("TERE", "loadRecipes()", it)
                    })
        )
    }

    private fun loadMobs() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getMobs(),
                mDataManager.getMobsFromDb(),
                BiFunction { mobsResponse: MobsResponse,
                             mobsDbList: List<MobsEntity> ->
                    if (mobsResponse.success.isSuccess())
                        mDataManager.containsData(mobsResponse.mobsLost, mobsDbList)
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showMessage(R.string.mobs_successfully_loaded)
                        viewState.showProgress(false)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.mobs_error_loaded)
                    })
        )
    }
}
