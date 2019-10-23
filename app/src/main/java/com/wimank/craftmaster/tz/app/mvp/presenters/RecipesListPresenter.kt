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

    private var recyclerPosition = 0

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initViews()
    }

    override fun attachView(view: RecipesListView?) {
        super.attachView(view)
        viewState.scrollRecyclerView(recyclerPosition)
    }

    fun chooseModification(section: String) {
        when (section) {
            MC_VALUE -> loadRecipesList(MC_VALUE)
            IC_VALUE -> loadRecipesList(IC_VALUE)
            BC_VALUE -> loadRecipesList(BC_VALUE)
            FR_VALUE -> loadRecipesList(FR_VALUE)
            MOBS_VALUE -> loadMobsList()
        }
    }

    private fun loadRecipesList(modification: String) {
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
                        viewState.showMessage(R.string.recipes_list_successfully_loaded)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipes_list_loaded_error)
                    })
        )
    }

    private fun loadMobsList() {
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
                        viewState.showMessage(R.string.mobs_successfully_loaded)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.mobs_error_loaded)
                    })
        )
    }

    fun saveRecyclerPosition(position: Int) {
        recyclerPosition = position
    }
}
