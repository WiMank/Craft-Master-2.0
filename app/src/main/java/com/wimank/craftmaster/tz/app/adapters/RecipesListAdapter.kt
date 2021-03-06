package com.wimank.craftmaster.tz.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.room.RecipesListItem

class RecipesListAdapter(
    private var mList: List<RecipesListItem>,
    private val mOnItemClickListener: OnItemClickListener,
    private val mLocaleManager: LocaleManager
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
        holder.bind(mList[position], mOnItemClickListener, mLocaleManager)
    }

    override fun getItemCount() = mList.size


    fun updateList(newList: List<RecipesListItem>) {
        with(DiffUtil.calculateDiff(SearchDiffUtil(mList, newList), true)) {
            mList = newList
            dispatchUpdatesTo(this@RecipesListAdapter)
        }
    }

    fun setData(dataList: List<RecipesListItem>) {
        mList = dataList
        this.notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: RecipesListItem)
    }
}
