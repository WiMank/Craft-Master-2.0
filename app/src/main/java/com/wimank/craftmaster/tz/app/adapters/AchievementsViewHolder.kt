package com.wimank.craftmaster.tz.app.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.app.di.modules.GlideApp
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.room.entity.AchievementEntity
import kotlinx.android.synthetic.main.achiv_item.view.*
import java.io.File

class AchievementsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(achievementEntity: AchievementEntity, localeManager: LocaleManager) {
        view.achiv_name.text = localeManager.localizeString(achievementEntity.achName)
        view.achiv_desc.text = localeManager.localizeString(achievementEntity.achDescription)
        view.modification.text = achievementEntity.achModification

        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME),
            "${achievementEntity.achImage}.png"
        )

        GlideApp
            .with(view)
            .load(targetImage)
            .into(view.achiv_image)
    }
}
