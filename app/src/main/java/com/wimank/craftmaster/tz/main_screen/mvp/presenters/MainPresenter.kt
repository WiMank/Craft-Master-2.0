package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


@InjectViewState
class MainPresenter(
    private val mainGroupManager: MainGroupManager) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadGroupList()
    }

    private fun loadGroupList() {
        unsubscribeOnDestroy(
            mainGroupManager
                .getMainGroup()
                .flatMap { t -> Single.just(t.groupList) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { list ->
                        if (!list.isNullOrEmpty()) {
                            list.forEach {
                                downloadGroupImages(it.groupImage)
                            }
                        }
                    },
                    onError = {
                        viewState.showError(R.string.group_list_load_error)
                    }
                )
        )
    }

    private fun downloadGroupImages(imageName: String) {
        unsubscribeOnDestroy(
            mainGroupManager.getMainGroupImage(imageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        mainGroupManager.prepareWriteImage(it.byteStream(), imageName)
                    },
                    onError = {
                        Log.e("TEST", "downloadGroupImages()", it)
                    }
                )
        )
    }
}