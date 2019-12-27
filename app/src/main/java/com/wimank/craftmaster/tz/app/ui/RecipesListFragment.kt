package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.adapters.RecipesListAdapter
import com.wimank.craftmaster.tz.app.mvp.models.LocaleManager
import com.wimank.craftmaster.tz.app.mvp.presenters.RecipesListPresenter
import com.wimank.craftmaster.tz.app.mvp.views.RecipesListView
import com.wimank.craftmaster.tz.app.room.RecipesListItem
import com.wimank.craftmaster.tz.app.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_recipes_list.*
import kotlinx.android.synthetic.main.fragment_recipes_list.view.*
import javax.inject.Inject

const val RL_KEY_MODIFICATION = "rl_key_modification"
const val ICONIFIED_SV = "iconifiedSV"

class RecipesListFragment : BaseFragment(), RecipesListView {

    @Inject
    @InjectPresenter
    lateinit var mRecipesListPresenter: RecipesListPresenter

    @ProvidePresenter
    fun providePresenter() = mRecipesListPresenter

    @Inject
    lateinit var mLocaleManager: LocaleManager

    private var mListenerRecipesList: OnRecipesListFragmentClickListener? = null

    private lateinit var mRecycler: RecyclerView

    private var mIsIconified: Boolean = true

    private lateinit var mModification: String

    private var mTextSearch: String = ""

    private lateinit var mSearchAdapter: RecipesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (savedInstanceState == null)
            arguments?.apply {
                getString(RL_KEY_MODIFICATION)?.let {
                    mRecipesListPresenter.chooseModification(it, getBoolean(ICONIFIED_SV))
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mSearchAdapter = RecipesListAdapter(
            listOf(), object : RecipesListAdapter.OnItemClickListener {
                override fun onItemClick(item: RecipesListItem) {
                    itemClick(item)
                }
            }, mLocaleManager
        )

        return inflater.inflate(R.layout.fragment_recipes_list, container, false).apply {
            mRecycler = this.recipes_list_recycler_view.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mSearchAdapter
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRecipesListFragmentClickListener)
            mListenerRecipesList = context
        else
            throw RuntimeException("$context must implement OnRecipesListFragmentClickListener")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.search_view)
        val searchView = item.actionView as SearchView
        searchView.isIconified = mIsIconified

        if (mTextSearch.isNotEmpty())
            searchView.setQuery(mTextSearch, false)

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty())
                    mRecipesListPresenter.loadSearchList(true, mModification)
                else
                    mRecipesListPresenter.loadSearchList(false, mModification, newText)

                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initViews() {
        recipes_list_refresh.setColorSchemeColors(Color.MAGENTA)
        recipes_list_refresh.isEnabled = false
    }

    override fun showMessage(message: Int) {
        Snackbar.make(recipes_list_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showError(message: Int) {
        Snackbar.make(recipes_list_ll, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgress(visibilityFlag: Boolean) {
        recipes_list_refresh.isRefreshing = visibilityFlag
    }

    override fun optionalTitleSetting(titleMod: String) {
        toolbarListener?.updateToolbarTitle(titleMod)
    }

    override fun setModVar(mod: String) {
        mModification = mod
    }

    override fun setIconifiedVar(isIconified: Boolean) {
        mIsIconified = isIconified
    }

    override fun setTextSearch(text: String) {
        mTextSearch = text
    }

    override fun showList(list: List<RecipesListItem>) {
        mSearchAdapter.setData(list)
    }

    override fun updateList(list: List<RecipesListItem>) {
        mSearchAdapter.updateList(list)
    }

    override fun onDetach() {
        super.onDetach()
        mListenerRecipesList = null
    }

    fun itemClick(recipesListItem: RecipesListItem) {
        mListenerRecipesList?.onRecipesListFragmentClick(recipesListItem)
    }

    interface OnRecipesListFragmentClickListener {
        fun onRecipesListFragmentClick(recipesListItem: RecipesListItem)
    }

}
