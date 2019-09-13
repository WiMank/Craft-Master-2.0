package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupModel
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


@InjectViewState
class MainPresenter(private val context: Context,
                    private val mainGroupModel: MainGroupModel) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadGroupList()
    }
    
    private fun loadGroupList(){
        unsubscribeOnDestroy(
            mainGroupModel
                .getMainGroup()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        if (!it.groupList.isNullOrEmpty()) {
                            viewState.groupListLoaded(it.groupList)
                            viewState.showMessage(R.string.groups_successfully_uploaded)
                        } else
                            viewState.showError(R.string.group_list_load_error)
                    },
                    onError = { viewState.showError(R.string.group_list_load_error) }
                )
        )
    }
}