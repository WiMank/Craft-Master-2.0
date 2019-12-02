package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.app.mvp.models.SearchManager
import com.wimank.craftmaster.tz.app.mvp.views.SearchView

@InjectViewState
class SearchPresenter(private val mSearchManager: SearchManager) : BasePresenter<SearchView>()
