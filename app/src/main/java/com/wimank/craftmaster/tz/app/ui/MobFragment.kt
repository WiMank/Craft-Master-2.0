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
import com.wimank.craftmaster.tz.app.mvp.common.MobImages
import com.wimank.craftmaster.tz.app.mvp.common.MobImages.*
import com.wimank.craftmaster.tz.app.mvp.presenters.MobsPresenter
import com.wimank.craftmaster.tz.app.mvp.views.MobsView
import com.wimank.craftmaster.tz.app.room.entity.MobsEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mobs.*
import java.io.File
import javax.inject.Inject

private const val MOB_FRAGMENT_KEY = "mob_key"
const val MOB_FRAGMENT_TAG = "MobFragment"

class MobFragment : BaseFragment(), MobsView {

    @Inject
    @InjectPresenter
    lateinit var mMobsPresenter: MobsPresenter

    @ProvidePresenter
    fun providePresenter() = mMobsPresenter

    private var listenerMobs: OnMobsFragmentClickListener? = null

    override fun iniViews(mobsEntity: MobsEntity) {
        mob_image.setOnClickListener { dropClick(mobsEntity.dropOne) }
        drop_img_one.setOnClickListener { dropClick(mobsEntity.dropTwo) }
        drop_img_two.setOnClickListener { dropClick(mobsEntity.dropThree) }
        drop_img_three.setOnClickListener { dropClick(mobsEntity.dropFour) }
        drop_img_four.setOnClickListener { dropClick(mobsEntity.dropFive) }
        drop_img_five.setOnClickListener { dropClick(mobsEntity.dropSix) }
    }

    override fun showMob(mobsEntity: MobsEntity) {
        setImage(mobsEntity.mobIcon, MOB_IMAGE)
        mob_health.text = mobsEntity.health
        mob_exp.text = mobsEntity.experience
        easy_attack_value.text = mobsEntity.easyAttack
        normal_attack_value.text = mobsEntity.normalAttack
        hard_attack_value.text = mobsEntity.hardAttack
        setImage(mobsEntity.dropTwo, DROP_ONE)
        setImage(mobsEntity.dropThree, DROP_TWO)
        setImage(mobsEntity.dropFour, DROP_THREE)
        setImage(mobsEntity.dropFive, DROP_FOUR)
        setImage(mobsEntity.dropSix, DROP_FIVE)
    }

    override fun showMobName(name: String) {
        mobName.text = name
    }

    override fun showMobDescription(description: String) {
        description_mob.text = description
    }

    override fun showMobType(type: String) {
        mobType.text = type
    }

    private fun setImage(imagePath: String, mobImages: MobImages) {
        val lContext: Context? = context
        if (lContext != null) {
            val targetImage =
                File(lContext.getExternalFilesDir(IMAGE_FOLDER_NAME), "${imagePath}.png")
            val glideApp = GlideApp.with(lContext).load(targetImage)
            when (mobImages) {
                MOB_IMAGE -> glideApp.into(mob_image)
                DROP_ONE -> glideApp.into(drop_img_one)
                DROP_TWO -> glideApp.into(drop_img_two)
                DROP_THREE -> glideApp.into(drop_img_three)
                DROP_FOUR -> glideApp.into(drop_img_four)
                DROP_FIVE -> glideApp.into(drop_img_five)
                DROP_SIX -> glideApp.into(drop_img_five)
            }
        }
    }

    override fun showMessage(message: Int) {
        Snackbar.make(mobs_sv, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun dropClick(recipeAttr: String) {
        listenerMobs?.modDropClickListener(recipeAttr)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMobsFragmentClickListener)
            listenerMobs = context
        else
            throw RuntimeException("$context must implement OnMobsFragmentClickListener")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(MOB_FRAGMENT_KEY)?.let {
            mMobsPresenter.loadMob(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mobs, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        listenerMobs = null
    }

    interface OnMobsFragmentClickListener {
        fun modDropClickListener(item: String)
    }

    companion object {
        fun newInstance(mob: String) =
            MobFragment().apply {
                arguments = Bundle().apply {
                    putString(MOB_FRAGMENT_KEY, mob)
                }
            }
    }
}
