package com.wimank.craftmaster.tz.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.room.RecipesListItem

class RecipesListAdapter(
    private var mList: List<RecipesListItem>,
    private val mOnItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecipesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListViewHolder {
        return RecipesListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.simple_recycler_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) {
        holder.bind(mList[position], mOnItemClickListener)
    }

    override fun getItemCount() = mList.size

    interface OnItemClickListener {
        fun onItemClick(item: RecipesListItem)
    }
}
