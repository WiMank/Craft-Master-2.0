package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.app.mvp.models.AchievementsManager
import com.wimank.craftmaster.tz.app.mvp.views.AchievementView

@InjectViewState
class AchievementsPresenter(val mAchievementsManager: AchievementsManager) :
    BasePresenter<AchievementView>()
