package com.wimank.craftmaster.tz.recipe_screen.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wimank.craftmaster.tz.R

class RecipeFragment : Fragment() {

    private var listenerRecipe: OnRecipeFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }


    fun onButtonPressed(uri: Uri) {
        listenerRecipe?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRecipeFragmentInteractionListener)
            listenerRecipe = context
        else
            throw RuntimeException("$context must implement OnRecipeFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        listenerRecipe = null
    }


    interface OnRecipeFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        fun newInstance(param1: String) =
            RecipeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
