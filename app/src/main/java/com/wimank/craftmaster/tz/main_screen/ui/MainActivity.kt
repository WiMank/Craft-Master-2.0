package com.wimank.craftmaster.tz.main_screen.ui

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.ui.BaseActivity
import com.wimank.craftmaster.tz.common.utils.LinearLayoutManagerWrapper
import com.wimank.craftmaster.tz.main_screen.adapter.MainGroupAdapter
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.CategoriesPresenter
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.mvp.views.CategoriesView
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView, CategoriesView {

    @Inject
    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter() = mMainPresenter

    @Inject
    @InjectPresenter
    lateinit var mCategoriesPresenter: CategoriesPresenter

    @ProvidePresenter
    fun providePresenter() = mCategoriesPresenter


    private lateinit var mAdapter: MainGroupAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        refresh.setOnRefreshListener {
            mMainPresenter.updateData()
        }
    }

    override fun showGroupList(list: List<MainGroupEntity>) {
        if (::mAdapter.isInitialized) {
            mAdapter.update(ArrayList(list))
        } else {
            mAdapter = MainGroupAdapter(ArrayList(list))
            group_recycler_view.apply {
                layoutManager = LinearLayoutManagerWrapper(this@MainActivity)
                adapter = mAdapter
            }
        }
    }

    override fun showMessage(message: Int) {
        Snackbar.make(main_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(visibilityFlag: Boolean) {
        refresh.isRefreshing = visibilityFlag
    }

    override fun showError(message: Int) {
        Snackbar.make(main_ll, message, Snackbar.LENGTH_SHORT).show()
    }
}