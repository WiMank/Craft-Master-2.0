package com.wimank.craftmaster.tz.app.recipe_screen.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.presenters.RecipePresenter
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.views.RecipeView
import javax.inject.Inject

class RecipeFragment : Fragment(), RecipeView {

    @Inject
    @InjectPresenter
    lateinit var mRecipePresenter: RecipePresenter

    @ProvidePresenter
    fun provideRecipePresenter() = mRecipePresenter

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

    override fun initViews() {

    }

    override fun showMessage(message: Int) {

    }

    override fun showError(message: Int) {

    }

    override fun showProgress(visibilityFlag: Boolean) {

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

    fun onItemClick(uri: Uri) {
        listenerRecipe?.onFragmentInteraction(uri)
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
