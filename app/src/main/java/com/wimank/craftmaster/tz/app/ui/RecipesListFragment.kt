package com.wimank.craftmaster.tz.app.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            arguments?.getString(RL_KEY_MODIFICATION)?.let {
                mRecipesListPresenter.chooseModification(it)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes_list, container, false).apply {
            mRecycler = this.recipes_list_recycler_view.apply {
                layoutManager = LinearLayoutManager(context)
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
        toolbarListener?.setToolbarTitle(titleMod)
    }

    override fun showList(list: List<RecipesListItem>) {
        mRecycler.apply {
            adapter = RecipesListAdapter(list,
                object :
                    RecipesListAdapter.OnItemClickListener {
                    override fun onItemClick(item: RecipesListItem) {
                        itemClick(item)
                    }
                }, mLocaleManager
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListenerRecipesList = null
    }

    fun itemClick(recipesListItem: RecipesListItem) {
        mListenerRecipesList?.onRecipesListFragmentClick(recipesListItem)
    }

    companion object {
        fun newInstance(section: String) =
            RecipesListFragment().apply {
                arguments = Bundle().apply {
                    putString(RL_KEY_MODIFICATION, section)
                }
            }
    }

    interface OnRecipesListFragmentClickListener {
        fun onRecipesListFragmentClick(recipesListItem: RecipesListItem)
    }

}
