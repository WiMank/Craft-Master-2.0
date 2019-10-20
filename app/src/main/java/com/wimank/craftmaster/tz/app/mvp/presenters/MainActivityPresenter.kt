package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.DataManager
import com.wimank.craftmaster.tz.app.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.app.rest.responses.RecipeResponse
import com.wimank.craftmaster.tz.app.room.entitys.DescriptionEntity
import com.wimank.craftmaster.tz.app.room.entitys.RecipeEntity
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.common.utils.NetManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
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
        viewState.initViews()
        updateData()
    }

    fun updateData() {
        viewState.showProgress(true)
        if (mNetManager.isInternetOn())
            loadRecipes()
        else
            viewState.showMessage(R.string.offline_mode)
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
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipes_list_loading_error)
                    })
        )
    }
}
