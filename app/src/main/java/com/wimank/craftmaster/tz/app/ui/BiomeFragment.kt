package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.di.modules.GlideApp
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.app.mvp.presenters.BiomesPresenter
import com.wimank.craftmaster.tz.app.mvp.views.BiomesView
import com.wimank.craftmaster.tz.app.room.entity.BiomesEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_biome.*
import kotlinx.android.synthetic.main.fragment_mobs.*
import java.io.File
import javax.inject.Inject

const val BIOME_KEY = "biome_key"

class BiomeFragment : BaseFragment(), BiomesView {

    @Inject
    @InjectPresenter
    lateinit var mBiomesPresenter: BiomesPresenter

    @ProvidePresenter
    fun providePresenter() = mBiomesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mBiomesPresenter.loadBiome(it.getString(BIOME_KEY, ""))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_biome, container, false)
    }

    override fun showBiome(biomeItem: BiomesEntity) {
        temperature_biome.text = biomeItem.biomeTemperature
        setImage(biomeItem.biomeImage)
    }

    override fun showBiomeName(biomeName: String) {
        biome_name.text = biomeName
    }

    override fun showBiomeDesc(biomeDesc: String) {
        descriptions_biome.text = biomeDesc
    }

    override fun showBiomeType(biomeType: String) {
        biome_type.text = biomeType
    }

    override fun showMessage(message: Int) {
        Snackbar.make(mobs_sv, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun setImage(imagePath: String) {
        val lContext: Context? = context
        if (lContext != null) {
            val targetImage =
                File(lContext.getExternalFilesDir(IMAGE_FOLDER_NAME), "${imagePath}.png")
            val glideApp = GlideApp.with(lContext).load(targetImage)
            glideApp.into(image_biome)
        }
    }

}
