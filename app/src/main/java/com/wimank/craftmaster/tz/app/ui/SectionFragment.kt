package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSectionFragmentClickListener)
            listenerSection = context
        else
            throw RuntimeException("$context must implement OnSectionFragmentClickListener")
    }

    override fun onDetach() {
        super.onDetach()
        listenerSection = null
    }

    override fun onResume() {
        super.onResume()
        toolbarListener?.setToolbarTitle(getString(R.string.app_name))
    }

    interface OnSectionFragmentClickListener {
        fun cardViewClick(section: String)
    }
}
