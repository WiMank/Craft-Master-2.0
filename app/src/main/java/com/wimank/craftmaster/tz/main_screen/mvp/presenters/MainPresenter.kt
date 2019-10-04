package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.categories_screen.rest.CategoryResponse
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.room.entities.McCategoryEntity
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.DataManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import com.wimank.craftmaster.tz.main_screen.rest.MainGroupResponse
import com.wimank.craftmaster.tz.recipe_screen.rest.RecipeResponse
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McDescriptionEntity
import com.wimank.craftmaster.tz.recipe_screen.room.entity.McRecipeEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


@InjectViewState
class MainPresenter(
    private val mDataManager: DataManager,
    private val mNetManager: NetManager
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateData()
        loadMainGroupFromDb()
    }

    fun updateData() {
        viewState.showProgress(true)
        if (mNetManager.isInternetOn())
            loadMainGroupList()
        else
            viewState.showMessage(R.string.offline_mode)
    }

    private fun loadMainGroupList() {
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getMainGroup(),
                mDataManager.getMainGroupFromDb(),
                BiFunction { sVer: MainGroupResponse, lVer: List<MainGroupEntity> ->
                    if (sVer.success.isSuccess())
                        mDataManager.containsData(sVer.groupList, lVer)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showMessage(R.string.groups_successfully_uploaded)
                        viewState.showProgress(true)
                        loadCategories()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.group_list_load_error)
                    })
        )
    }

    private fun loadCategories() {
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getMcCategory(),
                mDataManager.getMcCategoryFromDb(),
                BiFunction { serData: CategoryResponse<McCategoryEntity>,
                             locData: List<McCategoryEntity> ->
                    if (serData.success.isSuccess())
                        mDataManager.containsData(serData.mcCategoryList, locData)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showMessage(R.string.categories_successfully_uploaded)
                        viewState.showProgress(true)
                        loadRecipes()
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.categories_list_load_error)
                    })
        )
    }

    private fun loadRecipes() {
        unsubscribeOnDestroy(
            Single.zip(
                mDataManager.getRecipes(),
                mDataManager.getRecipeFromDb(),
                mDataManager.getDescriptionFromDb(),
                Function3 { servRecipes: RecipeResponse,
                            mcRecipeEntity: List<McRecipeEntity>,
                            mcDescriptionEntity: List<McDescriptionEntity> ->
                    if (servRecipes.success.isSuccess()) {
                        mDataManager.containsData(servRecipes.recipesList, mcRecipeEntity)
                        mDataManager.containsData(servRecipes.descriptionList, mcDescriptionEntity)
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
                        viewState.showError(R.string.recipe_loading_error)
                    })
        )
    }

    private fun loadMainGroupFromDb() {
        unsubscribeOnDestroy(
            mDataManager
                .getFlowableMainGroupFromDb()
                .subscribeOn(Schedulers.io())
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        if (it.isNotEmpty()) {
                            viewState.showGroupList(it)
                            viewState.showProgress(false)
                        }
                    },
                    onError = {
                        viewState.showError(R.string.failed_load_data_in_db)
                        viewState.showProgress(false)
                    })
        )
    }
}