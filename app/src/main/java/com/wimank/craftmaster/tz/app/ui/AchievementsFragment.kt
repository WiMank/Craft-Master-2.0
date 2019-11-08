package com.wimank.craftmaster.tz.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.presenters.AchievementsPresenter
import com.wimank.craftmaster.tz.app.mvp.views.AchievementView
import com.wimank.craftmaster.tz.app.room.entitys.AchievementEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import javax.inject.Inject

private const val ACH_PARAM = "Minecraft"
const val ACHIEVEMENTS_FR = "AchievementsFragment"

class AchievementsFragment : BaseFragment(), AchievementView {

    private var modification: String? = null

    @Inject
    @InjectPresenter
    lateinit var mAchievementsPresenter: AchievementsPresenter

    @ProvidePresenter
    fun providePresenter() = mAchievementsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            modification = it.getString(ACH_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_achievements, container, false)
    }

    override fun showAchievements(list: List<AchievementEntity>) {

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
