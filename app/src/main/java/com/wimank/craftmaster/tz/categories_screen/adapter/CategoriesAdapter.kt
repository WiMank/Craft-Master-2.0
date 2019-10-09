package com.wimank.craftmaster.tz.categories_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity

class CategoriesAdapter(
    private var mList: ArrayList<CategoryEntity>,
    private val mOnItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.simple_recycler_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(mList[position], mOnItemClickListener)
    }

    override fun getItemCount() = mList.size

    interface OnItemClickListener {
        fun onItemClick(item: CategoryEntity)
    }
}
