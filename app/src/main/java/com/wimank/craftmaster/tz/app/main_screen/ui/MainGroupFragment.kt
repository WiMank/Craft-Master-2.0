package com.wimank.craftmaster.tz.app.main_screen.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.main_screen.adapter.MainGroupAdapter
import com.wimank.craftmaster.tz.app.main_screen.mvp.presenters.MainPresenter
import com.wimank.craftmaster.tz.app.main_screen.mvp.views.MainView
import com.wimank.craftmaster.tz.app.main_screen.room.MainGroupEntity
import com.wimank.craftmaster.tz.common.ui.BaseFragment
import com.wimank.craftmaster.tz.common.utils.LinearLayoutManagerWrapper
import kotlinx.android.synthetic.main.fragment_main_group.*
import javax.inject.Inject

class MainGroupFragment : BaseFragment(), MainView {

    @Inject
    @InjectPresenter
    lateinit var mMainPresenter: MainPresenter

    @ProvidePresenter
    fun provideMainPresenter() = mMainPresenter

    private var mListenerMain: OnMainFragClickListener? = null
    private lateinit var mAdapter: MainGroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_group, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMainFragClickListener)
            mListenerMain = context
        else
            throw RuntimeException("$context must implement OnMainFragClickListener")
    }

    override fun initViews() {
        refresh.setColorSchemeColors(Color.RED)
        refresh.setOnRefreshListener {
            mMainPresenter.updateData()
        }
    }

    fun itemClick(mainGroupEntity: MainGroupEntity) {
        mListenerMain?.onMainFragmentClick(mainGroupEntity)
    }

    override fun showGroupList(list: List<MainGroupEntity>) {
        if (::mAdapter.isInitialized)
            mAdapter.update(ArrayList(list))
        else {
            mAdapter = MainGroupAdapter(ArrayList(list),
                object : MainGroupAdapter.OnItemClickListener {
                    override fun onItemClick(item: MainGroupEntity) {
                        itemClick(item)
                    }
                })

            main_group_recycler_view.apply {
                layoutManager = LinearLayoutManagerWrapper(context)
                adapter = mAdapter
            }
        }
    }

    override fun showMessage(message: Int) {
        Snackbar.make(main_fragment_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(visibilityFlag: Boolean) {
        refresh.isRefreshing = visibilityFlag
    }

    override fun showError(message: Int) {
        Snackbar.make(main_fragment_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    interface OnMainFragClickListener {
        fun onMainFragmentClick(mainGroupEntity: MainGroupEntity)
    }

    override fun onDetach() {
        super.onDetach()
        mListenerMain = null
    }
}
