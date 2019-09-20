package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import com.wimank.craftmaster.tz.main_screen.rest.response.GroupListItem
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
        getMainGroupFromDb()
        if (netManager.isInternetOn())
            loadGroupList()
        else
            viewState.showMessage(R.string.offline_mode)
    }

    private fun getMainGroupFromDb() {
        unsubscribeOnDestroy(
            mainGroupManager
                .getMainGroupFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = { viewState.showGroupList(it) },
                    onError = { viewState.showError(R.string.failed_load_data_in_db) }
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
                        viewState.showError(R.string.groups_successfully_uploaded)
                        if (!list.isNullOrEmpty()) checkItemsVersion(list)
                    },
                    onError = { viewState.showError(R.string.group_list_load_error) }
                )
        )
    }

    private fun checkItemsVersion(list: List<GroupListItem>) {
        unsubscribeOnDestroy(
            Completable.fromAction {
                with(mainGroupManager.checkItemsVersion(list)) {
                    if (isNotEmpty()) forEach { item -> downloadGroupImages(item) }
                }
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = { viewState.showError(R.string.failed_to_save_response) })
        )
    }

    private fun downloadGroupImages(groupListItem: GroupListItem) {
        unsubscribeOnDestroy(
            mainGroupManager.getMainGroupImage(groupListItem.groupImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { writeImageAndGroupListItem(it.byteStream(), groupListItem) },
                    onError = { viewState.showError(R.string.failed_to_download_images) }
                )
        )
    }

    private fun writeImageAndGroupListItem(inputStream: InputStream, groupListItem: GroupListItem) {
        unsubscribeOnDestroy(
            Completable
                .fromAction { mainGroupManager.writeResponse(inputStream, groupListItem) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = { viewState.showError(R.string.failed_to_save_response) })
        )
    }
}