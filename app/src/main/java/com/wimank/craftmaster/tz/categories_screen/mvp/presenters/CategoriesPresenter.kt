package com.wimank.craftmaster.tz.categories_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.categories_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.categories_screen.mvp.views.CategoriesView
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class CategoriesPresenter(private val mCategoriesManager: CategoriesManager) :
    BasePresenter<CategoriesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initViews()
    }

    fun loadCategories(group: String) {
        viewState.showProgress(true)
        unsubscribeOnDestroy(
            mCategoriesManager
                .getCategoriesByGroupName(group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showCategoryList(it)
                        viewState.showProgress(false)
                        viewState.showMessage(R.string.сategories_successfully_loaded)
                    },
                    onError = {
                        viewState.showProgress(false)
                        viewState.showError(R.string.сategories_upload_error)
                    }
                ))
    }
}
