package com.wimank.craftmaster.tz.main_screen.mvp

import android.content.Context
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.common.mvp.BasePresenter


@InjectViewState
class MainPresenter(private val context: Context) : BasePresenter<MainView>() {

    init {
        Log.i("TEST", "INIT presenter")
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showMessage("Kek!")
        Log.i("TEST", "onFirstViewAttach()")
    }


    fun kek(){
        viewState.showMessage("Kek!")
    }
}