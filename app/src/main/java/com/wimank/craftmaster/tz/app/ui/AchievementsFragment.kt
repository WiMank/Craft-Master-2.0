package com.wimank.craftmaster.tz.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.adapters.AchievementsAdapter
import com.wimank.craftmaster.tz.app.mvp.common.LinearLayoutManagerWrapper
import com.wimank.craftmaster.tz.app.mvp.presenters.AchievementsPresenter
import com.wimank.craftmaster.tz.app.mvp.views.AchievementView
import com.wimank.craftmaster.tz.app.room.entitys.AchievementEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_achievements.*
import javax.inject.Inject

const val ACHIEVEMENTS_FR = "AchievementsFragment"

class AchievementsFragment : BaseFragment(), AchievementView {

    @Inject
    @InjectPresenter
    lateinit var mAchievementsPresenter: AchievementsPresenter

    @ProvidePresenter
    fun providePresenter() = mAchievementsPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_achievements, container, false)
    }

    override fun showAchievements(list: List<AchievementEntity>) {
        achiv_recycler.apply {
            layoutManager = LinearLayoutManagerWrapper(context)
            adapter = AchievementsAdapter(list)
        }
    }

    override fun showError(message: Int) {
        Snackbar.make(achiv_ll, message, Snackbar.LENGTH_SHORT).show()
    }
}
