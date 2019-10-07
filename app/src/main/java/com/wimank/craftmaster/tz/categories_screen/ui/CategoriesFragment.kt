package com.wimank.craftmaster.tz.categories_screen.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.categories_screen.mvp.presenters.CategoriesPresenter
import com.wimank.craftmaster.tz.categories_screen.mvp.views.CategoriesView
import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.common.ui.BaseFragment
import javax.inject.Inject

private const val GROUP_KEY = "group_key"
const val CAT_FRAGMENT_TAG = "CategoriesFragment"

class CategoriesFragment : BaseFragment(), CategoriesView {

    @Inject
    @InjectPresenter
    lateinit var mCategoriesPresenter : CategoriesPresenter

    @ProvidePresenter
    fun providePresenter() = mCategoriesPresenter

    companion object {
        fun newInstance(group: String) = CategoriesFragment().apply {
            arguments?.putString(GROUP_KEY, group)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getString(GROUP_KEY)?.let { group -> mCategoriesPresenter.loadMcCategories(group) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_minecraft_categories, container, false)

        return view
    }

    override fun showCategoryList(list: List<CategoryEntity>) {

    }
}
