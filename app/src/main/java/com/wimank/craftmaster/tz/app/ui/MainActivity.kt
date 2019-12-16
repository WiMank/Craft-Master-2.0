package com.wimank.craftmaster.tz.app.ui

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.presenters.MainActivityPresenter
import com.wimank.craftmaster.tz.app.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseActivity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView,
    RecipesListFragment.OnRecipesListFragmentClickListener,
    RecipeFragment.OnRecipeFragmentClickListener,
    SectionFragment.OnSectionFragmentClickListener,
    MobFragment.OnMobsFragmentClickListener,
    BaseFragment.TitleListener,
    FavoriteListFragment.OnItemFavClickListener {

    @Inject
    @InjectPresenter
    lateinit var mMainActivityPresenter: MainActivityPresenter

    @ProvidePresenter
    fun providePresenter() = mMainActivityPresenter

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
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


    override fun showRecipesList(section: String, iconifiedSV: Boolean) {
        mNavController.navigate(
            R.id.action_sectionFragment_to_recipesListFragment,
            Bundle().apply {
                putString(RL_KEY_MODIFICATION, section)
                putBoolean(ICONIFIED_SV, iconifiedSV)
            }
        )
    }

    override fun showAchievementsSection() {
        mNavController.navigate(R.id.action_sectionFragment_to_achievementsFragment)
    }

    override fun showBrewingSection() {
        mNavController.navigate(R.id.action_sectionFragment_to_brewingFragment)
    }

    override fun onRecipesListFragmentClick(recipesListItem: RecipesListItem) {
        mMainActivityPresenter.choseListSection(recipesListItem.section, recipesListItem.attr)
    }

    override fun onRecipeFragmentClick(recipeAttr: String) {
        mNavController.navigate(
            R.id.action_recipeFragment_self,
            Bundle().apply {
                putString(RECIPE_FRAGMENT_KEY, recipeAttr)
            }
        )
    }

    override fun modDropClickListener(item: String) {
        showRecipeSection(item)
    }

    override fun showRecipeSection(item: String) {
        mNavController.navigate(
            R.id.action_recipesListFragment_to_recipeFragment,
            Bundle().apply {
                putString(RECIPE_FRAGMENT_KEY, item)
            }
        )
    }

    override fun showRecipeSectionFromFav(item: String) {
        mNavController.navigate(
            R.id.action_favoriteListFragment_to_recipeFragment,
            Bundle().apply {
                putString(RECIPE_FRAGMENT_KEY, item)
            }
        )
    }

    override fun showMobsSection(mob: String) {
        mNavController.navigate(
            R.id.action_recipesListFragment_to_mobFragment,
            Bundle().apply {
                putString(MOB_FRAGMENT_KEY, mob)
            }
        )
    }

    override fun showBiomesSection(biome: String) {
        mNavController.navigate(
            R.id.action_recipesListFragment_to_biomeFragment,
            Bundle().apply {
                putString(BIOME_KEY, biome)
            }
        )
    }

    override fun showFavoriteSection() {
        mNavController.navigate(R.id.action_sectionFragment_to_favoriteFragment)
    }

    override fun updateToolbarTitle(title: String) {
        setTitle(title)
    }

    override fun onFavItemClick(favoriteEntity: FavoriteEntity) {
        showRecipeSectionFromFav(favoriteEntity.fRecipeAttr)
    }

}
