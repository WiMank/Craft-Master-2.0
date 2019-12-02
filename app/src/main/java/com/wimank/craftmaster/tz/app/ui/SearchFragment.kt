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
import com.wimank.craftmaster.tz.app.mvp.presenters.SearchPresenter
import com.wimank.craftmaster.tz.app.mvp.views.SearchView
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import javax.inject.Inject

class SearchFragment : BaseFragment(), SearchView {

    @Inject
    @InjectPresenter
    lateinit var mSearchPresenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter() = mSearchPresenter

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener)
            listener = context
        else
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun showList(list: List<DescriptionEntity>) {

    }
}
