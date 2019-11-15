package com.wimank.craftmaster.tz.app.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.app.di.modules.GlideApp
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import com.wimank.craftmaster.tz.app.room.entity.AchievementEntity
import com.wimank.craftmaster.tz.app.utils.getCurrentLocale
import kotlinx.android.synthetic.main.achiv_item.view.*
import java.io.File

class AchievementsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        achievementEntity: AchievementEntity
    ) {
        view.achiv_name.text = localizedName(view.context, achievementEntity.achName)
        view.achiv_desc.text = localizedName(view.context, achievementEntity.achDescription)
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

    private fun localizedName(context: Context, name: LocalizedType): String {
        return when (getCurrentLocale(context).language) {
            "ru" -> name.ru
            "uk" -> name.ru
            else -> name.en
        }
    }
}
