package com.wimank.craftmaster.tz.app.recipe_screen.ui

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.models.RecipeImages
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.models.RecipeImages.*
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.presenters.RecipePresenter
import com.wimank.craftmaster.tz.app.recipe_screen.mvp.views.RecipeView
import com.wimank.craftmaster.tz.app.recipe_screen.room.DescriptionEntity
import com.wimank.craftmaster.tz.app.recipe_screen.room.RecipeEntity
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.ui.BaseFragment
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import kotlinx.android.synthetic.main.craft_table_layout.*
import kotlinx.android.synthetic.main.fragment_recipe.*
import java.io.File
import javax.inject.Inject

private const val RECIPE_FRAGMENT_KEY = "recipe_attr"
const val RECIPE_FRAGMENT_TAG = "RecipeFragment"

class RecipeFragment : BaseFragment(), RecipeView {

    @Inject
    @InjectPresenter
    lateinit var mRecipePresenter: RecipePresenter

    @ProvidePresenter
    fun provideRecipePresenter() = mRecipePresenter

    private var listenerRecipe: OnRecipeFragmentClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRecipeFragmentClickListener)
            listenerRecipe = context
        else
            throw RuntimeException("$context must implement OnRecipeFragmentClickListener")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            arguments!!.getString(RECIPE_FRAGMENT_KEY)!!.let {
                mRecipePresenter.lodRecipeAndDescription(it)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun initViews() {
        recipe_refresh.setColorSchemeColors(Color.CYAN)
        recipe_refresh.isEnabled = false
    }

    override fun showMessage(message: Int) {
        Snackbar.make(recipe_refresh, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showError(message: Int) {
        Snackbar.make(recipe_refresh, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(visibilityFlag: Boolean) {
        recipe_refresh.isRefreshing = visibilityFlag
    }

    override fun fillRecipeImages(entity: DescriptionEntity) {
        setImage(entity.recipeImageName, RECIPE_IMAGE)
        setImage(entity.leftParameterImage, LEFT_P_IMAGE)
        setImage(entity.recipeImageName, RESULT_IMAGE)
    }

    override fun fillCraftTable(entity: RecipeEntity) {
        setImage(entity.firstSlot, FIRST_SLOT)
        setImage(entity.secondSlot, SECOND_SLOT)
        setImage(entity.threeSlot, THREE_SLOT)
        setImage(entity.fourthSlot, FOURTH_SLOTH)
        setImage(entity.fifthSlot, FIFTH_SLOT)
        setImage(entity.sixthSlot, SIXT_SLOT)
        setImage(entity.seventhSlot, SEVEN_SLOT)
        setImage(entity.eighthSlot, EIGHTH_SLOT)
        setImage(entity.ninthSlot, NINTH_SLOT)
    }

    override fun showLocalizedName(name: String) {
        recipe_name.text = name
    }

    override fun showLocalizeDescription(desc: String) {
        description_craft.text = desc
    }

    override fun showLocalizeLeftPar(leftP: String) {
        left_parameter.text = leftP
    }

    override fun showLocalizeLeftParText(leftPText: String) {
        left_parameter_text.text = leftPText
    }

    override fun showLocalizeRightPar(rightP: String) {
        right_parameter.text = rightP
    }

    override fun showLocalizeRightParText(rightPText: String) {
        right_parameter_text.text = rightPText
    }

    private fun setImage(imagePath: String, recipeImages: RecipeImages) {
        val lContext: Context? = context
        if (lContext != null) {
            val targetImage =
                File(lContext.getExternalFilesDir(IMAGE_FOLDER_NAME), "${imagePath}.png")
            val glideApp = GlideApp.with(lContext).load(targetImage)
            when (recipeImages) {
                FIRST_SLOT -> glideApp.into(firstSlot)
                SECOND_SLOT -> glideApp.into(secondSlot)
                THREE_SLOT -> glideApp.into(threeSlot)
                FOURTH_SLOTH -> glideApp.into(fourthSlot)
                FIFTH_SLOT -> glideApp.into(fifthSlot)
                SIXT_SLOT -> glideApp.into(sixthSlot)
                SEVEN_SLOT -> glideApp.into(seventhSlot)
                EIGHTH_SLOT -> glideApp.into(eighthSlot)
                NINTH_SLOT -> glideApp.into(ninthSlot)
                RECIPE_IMAGE -> glideApp.into(recipe_image)
                LEFT_P_IMAGE -> glideApp.into(image_parameter)
                RESULT_IMAGE -> glideApp.into(recipe_result_craft)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerRecipe = null
    }

    fun onItemClick(uri: Uri) {
        listenerRecipe?.onRecipeFragmentClick(uri)
    }

    interface OnRecipeFragmentClickListener {
        fun onRecipeFragmentClick(uri: Uri)
    }

    companion object {
        fun newInstance(recipeAttr: String) =
            RecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(RECIPE_FRAGMENT_KEY, recipeAttr)
                }
            }
    }
}
