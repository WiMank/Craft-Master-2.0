package com.wimank.craftmaster.tz.main_screen.mvp.presenters

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import com.wimank.craftmaster.tz.common.utils.NetManager
import com.wimank.craftmaster.tz.main_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.main_screen.mvp.views.CategoriesView


@InjectViewState
class CategoriesPresenter(
    private val mContext: Context,
    private val mCategoriesManager: CategoriesManager,
    private val mNetManager: NetManager
) : BasePresenter<CategoriesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}