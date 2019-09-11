package com.wimank.craftmaster.tz.common.mvp

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import rx.Subscription
import rx.subscriptions.CompositeSubscription


abstract class RootPresenter<View : MvpView> : MvpPresenter<View>() {
    private val compositeSubscription = CompositeSubscription()

    fun unsubscribeOnDestroy(subscription: Subscription) {
        compositeSubscription.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeSubscription.clear()
    }
}