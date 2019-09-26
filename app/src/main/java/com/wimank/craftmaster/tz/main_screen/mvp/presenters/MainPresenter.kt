package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.common.room.entities.DbVersEntity
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.MainGroupManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import com.wimank.craftmaster.tz.main_screen.rest.response.DbVersResponse
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.io.InputStream
import java.util.concurrent.TimeUnit


@InjectViewState
class MainPresenter(
    private val mainGroupManager: MainGroupManager,
    private val netManager: NetManager
) : BasePresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateData()
        loadMainGroupFromDb()
    }

    fun updateData() {
        viewState.showProgress(true)
        if (netManager.isInternetOn())
            checkDbVersion()
        else
            viewState.showMessage(R.string.offline_mode)
    }

    private fun checkDbVersion() {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            Single.zip(
                mainGroupManager.getServerDbVersion(),
                mainGroupManager.getDbVersionFromDb(),
                BiFunction { sDb: DbVersResponse, lDb: DbVersEntity ->
                    if (sDb.success.isSuccess())
                        if (sDb.versionDb > lDb.versionDb) {
                            mainGroupManager.deleteMainGroupsFromDb()
                            mainGroupManager.updateDbVersionFromDb(sDb.versionDb, sDb.dbId)
                            loadGroupList()
                        }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        viewState.showError(R.string.database_version_check_error)
                        viewState.showProgress(false)
                    },
                    onSuccess = { viewState.showProgress(false) })
        )
    }

    private fun loadGroupList() {
        unsubscribeOnDestroy(
            mainGroupManager
                .getMainGroup()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
                .flatMap { t -> Observable.fromIterable(t.groupList) }
                .subscribeBy(
                    onNext = { downloadGroupImages(it) },
                    onError = { viewState.showError(R.string.group_list_load_error) }
                ))
    }

    private fun loadMainGroupFromDb() {
        unsubscribeOnDestroy(
            mainGroupManager
                .getFlowableMainGroupFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        if (it.isEmpty())
                            loadGroupList()
                        else {
                            viewState.showGroupList(it)
                            viewState.showProgress(false)
                        }
                    },
                    onError = {
                        viewState.showError(R.string.failed_load_data_in_db)
                        viewState.showProgress(false)
                    })
        )
    }

    private fun downloadGroupImages(mainGroupEntity: MainGroupEntity) {
        unsubscribeOnDestroy(
            mainGroupManager
                .getMainGroupImage(mainGroupEntity.groupImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { writeImageAndEntity(it.byteStream(), mainGroupEntity) },
                    onError = {
                        viewState.showError(R.string.failed_to_download_images)
                        viewState.showProgress(false)
                    })
        )
    }

    private fun writeImageAndEntity(inps: InputStream, entity: MainGroupEntity) {
        unsubscribeOnDestroy(
            Completable
                .fromAction { mainGroupManager.writeResponse(inps, entity) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                    viewState.showError(R.string.failed_to_save_response)
                    viewState.showProgress(false)
                })
        )
    }
}