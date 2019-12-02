package com.wimank.craftmaster.tz.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity

class FavoriteAdapter(
    private var mList: List<FavoriteEntity>,
    private val mOnItemClickListener: OnItemClickListener,
    private val mLocaleManager: LocaleManager
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.simple_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(mList[position], mOnItemClickListener, mLocaleManager)
    }

    fun updateList(newList: List<FavoriteEntity>) {
        with(DiffUtil.calculateDiff(FavoriteDiffUtil(mList, newList), true)) {
            mList = newList
            dispatchUpdatesTo(this@FavoriteAdapter)
        }
    }

    fun setData(dataList: List<FavoriteEntity>) {
        mList = dataList
        this.notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: FavoriteEntity)
    }
}
