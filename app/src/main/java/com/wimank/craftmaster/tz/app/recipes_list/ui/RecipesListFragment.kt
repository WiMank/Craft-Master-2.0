package com.wimank.craftmaster.tz.app.recipes_list.ui

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
import com.wimank.craftmaster.tz.app.recipes_list.adapter.RecipesListAdapter
import com.wimank.craftmaster.tz.app.recipes_list.mvp.presenter.RecipesListPresenter
import com.wimank.craftmaster.tz.app.recipes_list.mvp.views.RecipesListView
import com.wimank.craftmaster.tz.app.recipes_list.room.RecipesListItem
import com.wimank.craftmaster.tz.common.ui.BaseFragment
import com.wimank.craftmaster.tz.common.utils.LinearLayoutManagerWrapper
import kotlinx.android.synthetic.main.fragment_recipes_list.*
import javax.inject.Inject

private const val RL_KEY_GROUP = "rl_key_group"
private const val RL_KEY_CATEGORY = "rl_key_category"
const val RL_FRAGMENT_TAG = "RecipesListFragment"

class RecipesListFragment : BaseFragment(), RecipesListView {

    @Inject
    @InjectPresenter
    lateinit var mRecipesListPresenter: RecipesListPresenter

    @ProvidePresenter
    fun providePresenter() = mRecipesListPresenter

    private var listenerRecipesList: OnRecipesListFragmentClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            arguments?.getString(RL_KEY_GROUP)?.let { mRecipesListPresenter.loadRecipesList(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipes_list, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnRecipesListFragmentClickListener)
            listenerRecipesList = context
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

    override fun scrollRecyclerView(scrollY: Int) {
        recipes_list_recycler_view.smoothScrollBy(0, scrollY)
    }

    override fun showRecipesList(list: List<RecipesListItem>) {
        recipes_list_recycler_view.apply {
            layoutManager = LinearLayoutManagerWrapper(context)
            adapter = RecipesListAdapter(list,
                object : RecipesListAdapter.OnItemClickListener {
                    override fun onItemClick(item: RecipesListItem) {
                        itemClick(item)
                    }
                })
        }
    }

    override fun onPause() {
        super.onPause()
        mRecipesListPresenter.saveRecyclerPosition(recipes_list_recycler_view.computeVerticalScrollOffset())
    }

    override fun onDetach() {
        super.onDetach()
        listenerRecipesList = null
    }

    fun itemClick(item: RecipesListItem) {
        listenerRecipesList?.onRecipesListFragmentClick(item)
    }

    companion object {
        fun newInstance(group: String) =
            RecipesListFragment().apply {
                arguments = Bundle().apply {
                    putString(RL_KEY_GROUP, group)
                }
            }
    }

    interface OnRecipesListFragmentClickListener {
        fun onRecipesListFragmentClick(item: RecipesListItem)
    }
}
