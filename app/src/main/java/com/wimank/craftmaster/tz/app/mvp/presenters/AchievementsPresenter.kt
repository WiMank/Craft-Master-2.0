package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.AchievementsManager
import com.wimank.craftmaster.tz.app.mvp.views.AchievementView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class AchievementsPresenter(private val mAchievementsManager: AchievementsManager) :
    BasePresenter<AchievementView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadAchievements()
    }

    private fun loadAchievements() {
        unsubscribeOnDestroy(
            mAchievementsManager.getAchievementsFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showAchievements(it)
                    },
                    onError = {
                        viewState.showError(R.string.achievement_load_error)
                    }
                ))
    }
}
