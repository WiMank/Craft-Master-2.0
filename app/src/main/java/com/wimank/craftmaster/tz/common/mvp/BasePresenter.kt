package com.wimank.craftmaster.tz.common.mvp

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import rx.Subscription
import rx.subscriptions.CompositeSubscription

open class BasePresenter<View : MvpView> : MvpPresenter<View>() {
    private val compositeDisposable = CompositeDisposable()

    fun unsubscribeOnDestroy(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
