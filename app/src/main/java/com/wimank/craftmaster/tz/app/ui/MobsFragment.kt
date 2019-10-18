package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wimank.craftmaster.tz.R

class MobsFragment : Fragment() {
    private var listenerMobs: OnMobsFragmentClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobs, container, false)
    }

    fun itemClick(uri: Uri) {
        listenerMobs?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMobsFragmentClickListener) {
            listenerMobs = context
        } else {
            throw RuntimeException("$context must implement OnMobsFragmentClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerMobs = null
    }

    interface OnMobsFragmentClickListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(mob: String) =
            MobsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
