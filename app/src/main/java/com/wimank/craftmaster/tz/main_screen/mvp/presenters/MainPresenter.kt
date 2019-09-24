package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import io.reactivex.Completable
import io.reactivex.Observable
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
            loadMainGroupFromDb()
        }
    }

    fun loadGroupList() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            mainGroupManager
                .getMainGroup()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .flatMap { t -> Observable.fromIterable(t.groupList) }
                .subscribeBy(
                    onNext = { downloadGroupImages(it) },
                    onError = {
                        viewState.showError(R.string.group_list_load_error)
                        viewState.showProgress(false)
                    },
                    onComplete = { loadMainGroupFromDb() }
                )
        )
    }

    private fun loadMainGroupFromDb() {
        unsubscribeOnDestroy(
            mainGroupManager
                .getMainGroupFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showGroupList(it)
                        viewState.showProgress(false)
                    },
                    onError = {
                        viewState.showError(R.string.failed_load_data_in_db)
                        viewState.showProgress(false)
                    }
                )
        )
    }

    private fun downloadGroupImages(mainGroupEntity: MainGroupEntity) {
        unsubscribeOnDestroy(
            mainGroupManager.getMainGroupImage(mainGroupEntity.groupImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { writeImageAndGroupListItem(it.byteStream(), mainGroupEntity) },
                    onError = {
                        viewState.showError(R.string.failed_to_download_images)
                        viewState.showProgress(false)
                    }
                )
        )
    }

    private fun writeImageAndGroupListItem(inps: InputStream, entity: MainGroupEntity) {
        unsubscribeOnDestroy(
            Completable
                .fromAction { mainGroupManager.writeResponse(inps, entity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = {
                    viewState.showError(R.string.failed_to_save_response)
                    viewState.showProgress(false)
                })
        )
    }
}