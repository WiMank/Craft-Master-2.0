package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.*
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_section.view.*

class SectionFragment : BaseFragment() {

    private var listenerSection: OnSectionFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_section, container, false)
        view.minecraft.setOnClickListener { itemClick(MC_VALUE) }
        view.industrial_craft.setOnClickListener { itemClick(IC_VALUE) }
        view.build_craft.setOnClickListener { itemClick(BC_VALUE) }
        view.forestry.setOnClickListener { itemClick(FR_VALUE) }
        view.mobs.setOnClickListener { itemClick(MOBS_VALUE) }
        view.achievements.setOnClickListener { itemClick(ACHIEVEMENTS_VALUE) }
        view.biomes.setOnClickListener { itemClick(BIOMES_VALUE) }
        view.brewing.setOnClickListener { itemClick(BREWING_VALUE) }
        return view
    }

    private fun itemClick(section: String) {
        listenerSection?.cardViewClick(section)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.section_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_list -> listenerSection?.cardViewClick(FAVORITES_VALUE)
            R.id.search -> listenerSection?.cardViewClick(SEARCH_VALUE)
        }
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSectionFragmentClickListener)
            listenerSection = context
        else
            throw RuntimeException("$context must implement OnSectionFragmentClickListener")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDetach() {
        super.onDetach()
        listenerSection = null
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle(getString(R.string.app_name))
    }

    interface OnSectionFragmentClickListener {
        fun cardViewClick(section: String)
    }

}
