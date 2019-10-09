package com.wimank.craftmaster.tz.categories_screen.ui


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.categories_screen.adapter.CategoriesAdapter
import com.wimank.craftmaster.tz.categories_screen.mvp.presenters.CategoriesPresenter
import com.wimank.craftmaster.tz.categories_screen.mvp.views.CategoriesView
import com.wimank.craftmaster.tz.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.common.ui.BaseFragment
import com.wimank.craftmaster.tz.common.utils.LinearLayoutManagerWrapper
import kotlinx.android.synthetic.main.fragment_minecraft_categories.*
import javax.inject.Inject

private const val GROUP_KEY = "group_key"
const val CAT_FRAGMENT_TAG = "CategoriesFragment"

class CategoriesFragment : BaseFragment(), CategoriesView {

    @Inject
    @InjectPresenter
    lateinit var mCategoriesPresenter : CategoriesPresenter

    @ProvidePresenter
    fun providePresenter() = mCategoriesPresenter

    private var mListenerCategories: OnCategoriesFragmentInteractionListener? = null

    companion object {
        fun newInstance(group: String) = CategoriesFragment().apply {
            arguments = Bundle().apply {
                putString(GROUP_KEY, group)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCategoriesFragmentInteractionListener)
            mListenerCategories = context
        else
            throw RuntimeException("$context must implement OnMainFragmentInteractionListener")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString(GROUP_KEY)?.let { mCategoriesPresenter.loadCategories(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_minecraft_categories, container, false)
    }

    override fun initViews() {
        categories_swipe_l.setColorSchemeColors(Color.GREEN)
        categories_swipe_l.isEnabled = true
    }

    override fun showCategoryList(list: List<CategoryEntity>) {
        categories_recycler_view.apply {
            layoutManager = LinearLayoutManagerWrapper(context)
            adapter = CategoriesAdapter(ArrayList(list),
                object : CategoriesAdapter.OnItemClickListener {
                    override fun onItemClick(item: CategoryEntity) {
                        itemClick(item)
                    }
                })
        }
    }

    fun itemClick(categoryEntity: CategoryEntity) {
        mListenerCategories?.onFragmentInteraction(categoryEntity)
    }

    override fun showMessage(message: Int) {
        Snackbar.make(categories_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showError(message: Int) {
        Snackbar.make(categories_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(visibilityFlag: Boolean) {
        categories_swipe_l.isRefreshing = visibilityFlag
    }

    interface OnCategoriesFragmentInteractionListener {
        fun onFragmentInteraction(categoryEntity: CategoryEntity)
    }

    override fun onDetach() {
        super.onDetach()
        mListenerCategories = null
    }
}
