package com.wimank.craftmaster.tz.categories_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.categories_screen.mvp.models.CategoriesManager
import com.wimank.craftmaster.tz.categories_screen.mvp.views.CategoriesView
import com.wimank.craftmaster.tz.common.mvp.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


@InjectViewState
class CategoriesPresenter(private val mCategoriesManager: CategoriesManager) :
    BasePresenter<CategoriesView>() {

    fun loadMcCategories(group: String) {
        unsubscribeOnDestroy(
            mCategoriesManager
                .getMcCategoriesByGroupName(group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {

                    },
                    onError = {

                    }
                ))
    }
}