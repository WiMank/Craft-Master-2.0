package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.mvp.presenters.FavoritePresenter
import com.wimank.craftmaster.tz.app.mvp.views.FavoriteView
import com.wimank.craftmaster.tz.app.room.entity.FavoriteEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import javax.inject.Inject

class FavoriteFragment : BaseFragment(), FavoriteView {

    @Inject
    @InjectPresenter
    lateinit var mFavoritePresenter: FavoritePresenter

    @ProvidePresenter
    fun providePresenter() = mFavoritePresenter

    @Inject
    lateinit var mLocaleManager: LocaleManager

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        return view
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }


    override fun showMessage(message: Int) {

    }

    override fun showError(message: Int) {

    }

    override fun showProgress(visibilityFlag: Boolean) {

    }

    override fun showList(list: List<FavoriteEntity>) {

    }

    override fun optionalTitleSetting(titleMod: String) {

    }

}
