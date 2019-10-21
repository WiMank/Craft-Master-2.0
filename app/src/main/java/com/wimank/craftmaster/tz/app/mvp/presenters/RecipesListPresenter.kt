package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.BC_VALUE
import com.wimank.craftmaster.tz.app.mvp.common.FR_VALUE
import com.wimank.craftmaster.tz.app.mvp.common.IC_VALUE
import com.wimank.craftmaster.tz.app.mvp.common.MC_VALUE
import com.wimank.craftmaster.tz.app.mvp.models.RecipesListManager
import com.wimank.craftmaster.tz.app.mvp.views.RecipesListView
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
        }
    }

    private fun loadRecipesList(modification: String) {
        unsubscribeOnDestroy(
            mRecipesListManager
                .getRecipesList(modification)
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

    fun saveRecyclerPosition(position: Int) {
        recyclerPosition = position
    }
}
