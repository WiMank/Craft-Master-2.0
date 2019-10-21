package com.wimank.craftmaster.tz.app.ui

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.Sections
import com.wimank.craftmaster.tz.app.mvp.presenters.MainActivityPresenter
import com.wimank.craftmaster.tz.app.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.app.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView,
    RecipesListFragment.OnRecipesListFragmentClickListener,
    RecipeFragment.OnRecipeFragmentClickListener,
    SectionFragment.OnSectionFragmentClickListener {

    @Inject
    @InjectPresenter
    lateinit var mMainActivityPresenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter() = mMainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            initViews()
    }

    override fun initViews() {
        main_refresh.setColorSchemeColors(Color.RED)
        main_refresh.setOnRefreshListener {
            mMainActivityPresenter.updateData()
        }
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, SectionFragment())
            commit()
        }
    }

    override fun showMessage(message: Int) {
        Snackbar.make(main_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showError(message: Int) {
        Snackbar.make(main_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(visibilityFlag: Boolean) {
        main_refresh.isRefreshing = visibilityFlag
    }

    override fun cardViewClick(sections: Sections) {

    }

    override fun onRecipesListFragmentClick(item: RecipesListItem) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, RecipeFragment.newInstance(item.recipeAttr))
            addToBackStack(RECIPE_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onRecipeFragmentClick(uri: Uri) {

    }
}
