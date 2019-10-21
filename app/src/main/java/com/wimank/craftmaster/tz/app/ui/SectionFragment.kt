package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.common.Sections
import com.wimank.craftmaster.tz.app.mvp.common.Sections.*
import kotlinx.android.synthetic.main.fragment_section.*

class SectionFragment : Fragment() {

    private var listenerSection: OnSectionFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_section, container, false)
        minecraft.setOnClickListener { itemClick(MINECRAFT) }
        industrial_craft.setOnClickListener { itemClick(INDUSTRIAL_CRAFT) }
        build_craft.setOnClickListener { itemClick(BUILD_CRAFT) }
        forestry.setOnClickListener { itemClick(FORESTRY) }
        return view
    }

    private fun itemClick(sections: Sections) {
        listenerSection?.cardViewClick(sections)
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

    interface OnSectionFragmentClickListener {
        fun cardViewClick(sections: Sections)
    }
}
