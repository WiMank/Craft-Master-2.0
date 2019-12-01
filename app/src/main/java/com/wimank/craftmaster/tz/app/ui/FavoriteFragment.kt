package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.adapters.FavoriteAdapter
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.mvp.presenters.FavoritePresenter
import com.wimank.craftmaster.tz.app.mvp.views.FavoriteView
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import javax.inject.Inject

const val FAV_FRAGMENT_TAG = "FavoriteFragment"

class FavoriteFragment : BaseFragment(), FavoriteView {

    @Inject
    @InjectPresenter
    lateinit var mFavoritePresenter: FavoritePresenter

    @ProvidePresenter
    fun providePresenter() = mFavoritePresenter

    @Inject
    lateinit var mLocaleManager: LocaleManager

    private var mListener: OnItemFavClickListener? = null

    lateinit var mFavRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false).apply {
            mFavRecycler = this.fav_recycler.apply {
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    fun onFavItemPressed(favoriteEntity: FavoriteEntity) {
        mListener?.onFavItemClick(favoriteEntity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemFavClickListener)
            mListener = context
        else
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun showMessage(message: Int) {

    }

    override fun showError(message: Int) {

    }

    override fun showProgress(visibilityFlag: Boolean) {

    }

    override fun showList(list: List<FavoriteEntity>) {
        mFavRecycler.apply {
            adapter = FavoriteAdapter(list, object : FavoriteAdapter.OnItemClickListener {
                override fun onItemClick(item: FavoriteEntity) {
                    onFavItemPressed(item)
                }
            }, mLocaleManager)
        }
    }

    override fun optionalTitleSetting(titleMod: String) {
        toolbarListener?.setToolbarTitle(titleMod)
    }


    interface OnItemFavClickListener {
        fun onFavItemClick(favoriteEntity: FavoriteEntity)
    }
}
