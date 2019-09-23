package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.io.InputStream


@InjectViewState
class MainPresenter(
    private val mainGroupManager: MainGroupManager,
    private val netManager: NetManager
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (netManager.isInternetOn())
            loadGroupList()
        else {
            viewState.showMessage(R.string.offline_mode)
            getMainGroupFromDb()
        }
    }

    private fun getMainGroupFromDb() {
        unsubscribeOnDestroy(
            mainGroupManager
                .getFlowableMainGroupFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { viewState.showGroupList(it) },
                    onError = {
                        viewState.showError(R.string.failed_load_data_in_db)
                        Log.e("TEST", "getMainGroupFromDb()", it)}
                )
        )
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
                        if (list.isNotEmpty()) {
                            diffItemsVersion(list)
                            viewState.showError(R.string.groups_successfully_uploaded)
                        } else
                            viewState.showError(R.string.group_list_load_error)
                    },
                    onError = {
                        Log.e("TEST", "loadGroupList()", it)
                        viewState.showError(R.string.group_list_load_error)
                    }
                )
        )
    }

    private fun diffItemsVersion(list: List<MainGroupEntity>) {
        unsubscribeOnDestroy(
            Single
                .fromCallable { mainGroupManager.diffItemsVersion(list)  }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { viewState.showDiffGroupList(list, it) },
                    onError = {
                    viewState.showError(R.string.failed_to_save_response)
                    Log.e("TEST", "diffItemsVersion()", it)
                })
        )
    }

    private fun downloadGroupImages(mainGroupEntity: MainGroupEntity) {
        unsubscribeOnDestroy(
            mainGroupManager.getMainGroupImage(mainGroupEntity.groupImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { writeImageAndGroupListItem(it.byteStream(), mainGroupEntity) },
                    onError = { viewState.showError(R.string.failed_to_download_images) }
                )
        )
    }

    private fun writeImageAndGroupListItem(
        inputStream: InputStream,
        mainGroupEntity: MainGroupEntity
    ) {
        unsubscribeOnDestroy(
            Completable
                .fromAction { mainGroupManager.writeResponse(inputStream, mainGroupEntity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = {
                    Log.e("TEST", "writeImageAndGroupListItem()", it)
                    viewState.showError(R.string.failed_to_save_response)
                })
        )
    }
}