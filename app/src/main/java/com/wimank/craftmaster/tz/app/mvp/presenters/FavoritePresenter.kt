package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.app.mvp.common.FAVORITES_VALUE
import com.wimank.craftmaster.tz.app.mvp.models.FavoriteManager
import com.wimank.craftmaster.tz.app.mvp.views.FavoriteView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class FavoritePresenter(private val mFavoriteManager: FavoriteManager) :
    BasePresenter<FavoriteView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadFavoriteList()
    }

    private fun loadFavoriteList() {
        viewState.optionalTitleSetting(FAVORITES_VALUE)
        unsubscribeOnDestroy(
            mFavoriteManager.getFavoritesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showList(it)
                        viewState.showProgress(false)
                    },
                    onError = {
                        viewState.showProgress(false)
                    }
                ))
    }
}
