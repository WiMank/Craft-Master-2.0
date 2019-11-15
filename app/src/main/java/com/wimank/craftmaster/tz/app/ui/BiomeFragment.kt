package com.wimank.craftmaster.tz.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wimank.craftmaster.tz.R

private const val BIOME_KEY = "biome_key"

class BiomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_biome, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(biome: String) =
            BiomeFragment().apply {
                arguments = Bundle().apply {
                    putString(BIOME_KEY, biome)
                }
            }
    }
}
