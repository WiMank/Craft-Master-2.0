package com.wimank.craftmaster.tz.app.main_screen.ui

import android.net.Uri
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.app.categories_screen.ui.CAT_FRAGMENT_TAG
import com.wimank.craftmaster.tz.app.categories_screen.ui.CategoriesFragment
import com.wimank.craftmaster.tz.app.main_screen.mvp.presenters.MainActivityPresenter
import com.wimank.craftmaster.tz.app.main_screen.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.app.main_screen.room.MainGroupEntity
import com.wimank.craftmaster.tz.app.recipe_screen.ui.RECIPE_FRAGMENT_TAG
import com.wimank.craftmaster.tz.app.recipe_screen.ui.RecipeFragment
import com.wimank.craftmaster.tz.app.recipes_list.room.RecipesListItem
import com.wimank.craftmaster.tz.app.recipes_list.ui.RL_FRAGMENT_TAG
import com.wimank.craftmaster.tz.app.recipes_list.ui.RecipesListFragment
import com.wimank.craftmaster.tz.common.ui.BaseActivity

class MainActivity : BaseActivity(), MainActivityView,
    MainGroupFragment.OnMainFragClickListener,
    CategoriesFragment.OnCategoriesFragmentClickListener,
    RecipesListFragment.OnRecipesListFragmentClickListener,
    RecipeFragment.OnRecipeFragmentClickListener {

    @InjectPresenter
    lateinit var mMainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            initViews()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, MainGroupFragment())
            commit()
        }
    }

    override fun onMainFragmentClick(mainGroupEntity: MainGroupEntity) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, CategoriesFragment.newInstance(mainGroupEntity.group))
            addToBackStack(CAT_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onCategoriesFragmentClick(categoryEntity: CategoryEntity) {
        mMainActivityPresenter.checkCategoryAndGroup(categoryEntity)
    }

    override fun openBlocksandItemsCategory(categoryEntity: CategoryEntity) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, RecipesListFragment.newInstance(categoryEntity.group))
            addToBackStack(RL_FRAGMENT_TAG)
            commit()
        }
    }

    override fun openMobsCategory() {

    }

    override fun openBiomesCategory() {

    }

    override fun openAchievementsCategory(categoryEntity: CategoryEntity) {

    }

    override fun openPotionsCategory() {

    }

    override fun openComandsCategory() {

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
