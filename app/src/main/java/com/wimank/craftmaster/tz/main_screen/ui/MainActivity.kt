package com.wimank.craftmaster.tz.main_screen.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.categories_screen.ui.CAT_FRAGMENT_TAG
import com.wimank.craftmaster.tz.categories_screen.ui.CategoriesFragment
import com.wimank.craftmaster.tz.main_screen.room.MainGroupEntity


class MainActivity : AppCompatActivity(), MainGroupFragment.OnMainFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, MainGroupFragment())
            addToBackStack(MAIN_FRAGMENT_TAG)
            commit()
        }
    }

    override fun onFragmentInteraction(mainGroupEntity: MainGroupEntity) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.main_frame, CategoriesFragment.newInstance(mainGroupEntity.group))
            addToBackStack(CAT_FRAGMENT_TAG)
            commit()
        }
    }
}