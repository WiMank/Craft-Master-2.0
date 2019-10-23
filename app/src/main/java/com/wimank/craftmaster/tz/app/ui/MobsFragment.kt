package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.presenters.MobsPresenter
import com.wimank.craftmaster.tz.app.mvp.views.MobsView
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import javax.inject.Inject

class MobsFragment : BaseFragment(), MobsView {

    @Inject
    @InjectPresenter
    lateinit var mMobsPresenter: MobsPresenter

    @ProvidePresenter
    fun providePresenter() = mMobsPresenter

    private var listenerMobs: OnMobsFragmentClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobs, container, false)
    }

    fun itemClick(uri: Uri) {
        listenerMobs?.modDropClickListener(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMobsFragmentClickListener) {
            listenerMobs = context
        } else {
            throw RuntimeException("$context must implement OnMobsFragmentClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerMobs = null
    }

    interface OnMobsFragmentClickListener {
        fun modDropClickListener(uri: Uri)
    }

    companion object {
        fun newInstance(mob: String) =
            MobsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
