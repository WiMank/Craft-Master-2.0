package com.wimank.craftmaster.tz.main_screen.ui

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.room.entities.MainGroupEntity
import com.wimank.craftmaster.tz.common.ui.BaseActivity
import com.wimank.craftmaster.tz.main_screen.adapter.MainGroupAdapter
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.main_screen.mvp.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainView {

    @Inject
    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter() = mMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }


    private fun initView() {
        refresh.setOnRefreshListener {
            mMainPresenter.loadGroupList()
        }
    }

    override fun showGroupList(mainGroupEntity: List<MainGroupEntity>) {
        group_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainGroupAdapter(mainGroupEntity)
        }
    }

    override fun showDiffGroupList(
        mainGroupEntity: List<MainGroupEntity>,
        diffResult: DiffUtil.DiffResult
    ) {

    }

    override fun showMessage(message: Int) {
        Snackbar.make(main_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(visibilityFlag: Int) {

    }

    override fun showError(message: Int) {
        Snackbar.make(main_ll, message, Snackbar.LENGTH_SHORT).show()
    }
}