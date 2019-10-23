package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.app.mvp.models.MobsManager
import com.wimank.craftmaster.tz.app.mvp.views.MobsView

@InjectViewState
class MobsPresenter(private val mMobsManager: MobsManager) : BasePresenter<MobsView>()
