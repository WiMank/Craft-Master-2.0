package com.wimank.craftmaster.tz.main_screen.ui

import android.os.Bundle
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.categories_screen.ui.CAT_FRAGMENT_TAG
import com.wimank.craftmaster.tz.categories_screen.ui.CategoriesFragment
import com.wimank.craftmaster.tz.common.ui.BaseActivity
import com.wimank.craftmaster.tz.main_screen.room.MainGroupEntity
import com.wimank.craftmaster.tz.recipes_list.room.RecipesListItem
import com.wimank.craftmaster.tz.recipes_list.ui.RL_FRAGMENT_TAG
import com.wimank.craftmaster.tz.recipes_list.ui.RecipesListFragment

class MainActivity : BaseActivity(),
    MainGroupFragment.OnMainFragmentInteractionListener,
    CategoriesFragment.OnCategoriesFragmentInteractionListener,
    RecipesListFragment.OnRecipesListFragmentInteractionListener {

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

    override fun onFragmentInteraction(mainGroupEntity: MainGroupEntity) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, CategoriesFragment.newInstance(mainGroupEntity.group))
            addToBackStack(CAT_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onFragmentInteraction(categoryEntity: CategoryEntity) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_frame, RecipesListFragment.newInstance(categoryEntity.group))
            addToBackStack(RL_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onFragmentInteraction(item: RecipesListItem) {

    }
}
