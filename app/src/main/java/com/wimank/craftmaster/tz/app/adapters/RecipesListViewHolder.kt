package com.wimank.craftmaster.tz.app.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.app.rest.responses.RecipeName
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.common.utils.getCurrentLocale
import kotlinx.android.synthetic.main.simple_recycler_item.view.*
import java.io.File

class RecipesListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        recipesListItem: RecipesListItem,
        onItemClickListener: RecipesListAdapter.OnItemClickListener
    ) {
        view.item_name.text = localizedName(view.context, recipesListItem.recipeName)
        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME),
            "${recipesListItem.recipeImageName}.png"
        )

        GlideApp
            .with(view)
            .load(targetImage)
            .into(view.item_image)

        view.ll_rec.setOnClickListener {
            onItemClickListener.onItemClick(recipesListItem)
        }
    }

    private fun localizedName(context: Context, recipeName: RecipeName): String {
        return when (getCurrentLocale(context).language) {
            "ru" -> recipeName.ru
            "uk" -> recipeName.ru
            else -> recipeName.en
        }
    }
}
