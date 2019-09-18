package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import io.reactivex.Completable
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
    
    private fun loadGroupList(){
        unsubscribeOnDestroy(
            mainGroupManager
                .getMainGroup()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        if (!it.groupList.isNullOrEmpty()) {
                            viewState.groupListLoaded(it.groupList)
                            viewState.showMessage(R.string.groups_successfully_uploaded)
                            Completable.fromAction { mainGroupManager.writeResponse(it) }
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeBy(
                                    onError = {
                                        viewState.showError(R.string.group_list_load_error)
                                    }
                                )
                        } else
                            viewState.showError(R.string.group_list_load_error)
                    },
                    onError = {
                        viewState.showError(R.string.group_list_load_error)
                    }
                )
        )
    }
}