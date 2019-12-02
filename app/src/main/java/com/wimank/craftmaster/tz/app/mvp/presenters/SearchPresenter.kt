package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.app.mvp.models.SearchManager
import com.wimank.craftmaster.tz.app.mvp.views.SearchView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class SearchPresenter(private val mSearchManager: SearchManager) : BasePresenter<SearchView>() {

    fun loadAllRecipes() {
        unsubscribeOnDestroy(
            mSearchManager.getAllRecipes()
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showList(it)
                    },
                    onError = {

                    }
                ))
    }

}
