package com.wimank.craftmaster.tz.app.recipes_list.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.recipes_list.mvp.models.RecipesListManager
import com.wimank.craftmaster.tz.app.recipes_list.mvp.views.RecipesListView
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
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

    fun loadRecipesList(group: String) {
        unsubscribeOnDestroy(
            mRecipesListManager
                .getRecipesList(group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showRecipesList(it)
                        viewState.showProgress(false)
                        viewState.showMessage(R.string.recipes_list_successfully_loaded)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.recipes_list_loaded_error)
                    })
        )
    }
}
