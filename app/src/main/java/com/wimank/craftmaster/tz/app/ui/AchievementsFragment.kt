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
import com.wimank.craftmaster.tz.app.mvp.presenters.AchievementsPresenter
import com.wimank.craftmaster.tz.app.mvp.views.AchievementView
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import javax.inject.Inject

private const val ACH_PARAM = "Minecraft"

class AchievementsFragment : BaseFragment(), AchievementView {
    private var param1: String? = null

    private var listener: OnFragmentInteractionListener? = null

    @Inject
    @InjectPresenter
    lateinit var mAchievementsPresenter: AchievementsPresenter

    @ProvidePresenter
    fun providePresenter() = mAchievementsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ACH_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_achievements, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(achievement: String) =
            AchievementsFragment().apply {
                arguments = Bundle().apply {
                    putString(ACH_PARAM, achievement)
                }
            }
    }
}
