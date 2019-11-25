package com.wimank.craftmaster.tz.app.ui.base

import android.content.Context
import com.arellomobile.mvp.MvpAppCompatFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : MvpAppCompatFragment(), HasAndroidInjector {

    @Inject
    internal lateinit var androidInjector: DispatchingAndroidInjector<Any>

    internal var toolbarListener: TitleListener? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is TitleListener)
            toolbarListener = context
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onDetach() {
        super.onDetach()
        toolbarListener = null
    }

    interface TitleListener {
        fun setToolbarTitle(title: String)
    }
}
