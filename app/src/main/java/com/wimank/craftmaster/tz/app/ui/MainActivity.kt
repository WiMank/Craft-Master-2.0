package com.wimank.craftmaster.tz.app.ui

import android.os.Bundle
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.presenters.MainActivityPresenter
import com.wimank.craftmaster.tz.app.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.app.ui.base.BaseActivity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView,
    RecipesListFragment.OnRecipesListFragmentClickListener,
    RecipeFragment.OnRecipeFragmentClickListener,
    SectionFragment.OnSectionFragmentClickListener,
    MobFragment.OnMobsFragmentClickListener, BaseFragment.TitleListener {

    @Inject
    @InjectPresenter
    lateinit var mMainActivityPresenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter() = mMainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
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
        progressBar.isVisible = visibilityFlag
    }

    override fun cardViewClick(section: String) {
        mMainActivityPresenter.chooseCardViewSection(section)
    }


    override fun showRecipesListFragment(section: String) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, RecipesListFragment.newInstance(section))
            addToBackStack(RL_KEY_MODIFICATION)
            commit()
        }
    }

    override fun showAchievementsSection() {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, AchievementsFragment())
            addToBackStack(ACHIEVEMENTS_FR)
            commit()
        }
    }

    override fun showBrewingSection() {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, BrewingFragment())
            addToBackStack(BREWING_FR)
            commit()
        }
    }

    override fun onRecipesListFragmentClick(recipesListItem: RecipesListItem) {
        mMainActivityPresenter.choseListSection(recipesListItem.section, recipesListItem.attr)
    }

    override fun onRecipeFragmentClick(recipeAttr: String) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, RecipeFragment.newInstance(recipeAttr))
            addToBackStack(RECIPE_FRAGMENT_TAG)
            commit()
        }
    }

    override fun modDropClickListener(item: String) {
        showBlockAndItemsSection(item)
    }

    override fun showBlockAndItemsSection(item: String) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, RecipeFragment.newInstance(item))
            addToBackStack(RECIPE_FRAGMENT_TAG)
            commit()
        }
    }

    override fun showMobsSection(mob: String) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, MobFragment.newInstance(mob))
            addToBackStack(MOB_FRAGMENT_TAG)
            commit()
        }
    }

    override fun showBiomesSection(biome: String) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, BiomeFragment.newInstance(biome))
            addToBackStack(MOB_FRAGMENT_TAG)
            commit()
        }
    }

    override fun setToolbarTitle(title: String) {
        setTitle(title)
    }

}
