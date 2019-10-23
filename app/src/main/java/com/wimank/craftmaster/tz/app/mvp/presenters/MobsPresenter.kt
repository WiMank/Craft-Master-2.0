package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.MobsManager
import com.wimank.craftmaster.tz.app.mvp.views.MobsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MobsPresenter(private val mMobsManager: MobsManager) : BasePresenter<MobsView>() {

    fun loadMob(mob: String) {
        unsubscribeOnDestroy(
            mMobsManager
                .getMob(mob)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.iniViews(it)
                        viewState.showMob(it)
                        viewState.showMobName(mMobsManager.localizeString(it.mobName))
                        viewState.showMobType((mMobsManager.localizeString(it.typeMob)))
                        viewState.showMobDescription((mMobsManager.localizeString(it.description)))
                    },
                    onError = {
                        viewState.showMessage(R.string.mob_load_error)
                    })
        )
    }
}
