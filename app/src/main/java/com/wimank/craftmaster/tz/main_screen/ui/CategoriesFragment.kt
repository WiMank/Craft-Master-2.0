package com.wimank.craftmaster.tz.main_screen.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.common.ui.BaseFragment
import com.wimank.craftmaster.tz.main_screen.mvp.presenters.CategoriesPresenter
import com.wimank.craftmaster.tz.main_screen.mvp.views.CategoriesView
import javax.inject.Inject

private const val ENDPOINT_KEY = "endpoint"

class CategoriesFragment : BaseFragment(), CategoriesView {

    @Inject
    @InjectPresenter
    lateinit var mCategoriesPresenter : CategoriesPresenter

    @ProvidePresenter
    fun providePresenter() = mCategoriesPresenter

    companion object {
        fun newInstance(endPoint: String) =
            CategoriesFragment().apply {
                arguments = Bundle().apply {
                    putString(ENDPOINT_KEY, endPoint)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_minecraft_categories, container, false)
    }
}
