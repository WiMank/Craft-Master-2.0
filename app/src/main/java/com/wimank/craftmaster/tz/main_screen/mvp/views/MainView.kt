package com.wimank.craftmaster.tz.main_screen.mvp.views

import com.wimank.craftmaster.tz.common.mvp.BaseView
import com.wimank.craftmaster.tz.main_screen.room.MainGroupEntity

interface MainView : BaseView {

    fun initViews()

    override fun showMessage(message: Int)

    override fun showError(message: Int)

    override fun showProgress(visibilityFlag: Boolean)

    fun showGroupList(list: List<MainGroupEntity>)

}