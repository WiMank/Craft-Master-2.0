package com.wimank.craftmaster.tz.app.categories_screen.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.app.categories_screen.room.Category
import com.wimank.craftmaster.tz.app.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.common.di.GlideApp
import com.wimank.craftmaster.tz.common.utils.IMAGE_FOLDER_NAME
import com.wimank.craftmaster.tz.common.utils.getCurrentLocale
import kotlinx.android.synthetic.main.simple_recycler_item.view.*
import java.io.File

class CategoriesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        categoryEntity: CategoryEntity,
        onItemClickListener: CategoriesAdapter.OnItemClickListener
    ) {
        view.item_name.text = localizedName(view.context, categoryEntity.category)
        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME),
            "${categoryEntity.categoryImage}.png"
        )

        GlideApp
            .with(view)
            .load(targetImage)
            .into(view.item_image)

        view.ll_rec.setOnClickListener {
            onItemClickListener.onItemClick(categoryEntity)
        }
    }

    private fun localizedName(context: Context, category: Category): String {
        return when (getCurrentLocale(context).language) {
            "ru" -> category.ru
            "uk" -> category.ru
            else -> category.en
        }
    }
}
