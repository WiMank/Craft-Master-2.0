package com.wimank.craftmaster.tz.app.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.mvp.models.BiomeManager
import com.wimank.craftmaster.tz.app.mvp.views.BiomesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class BiomesPresenter(private val mBiomeManager: BiomeManager) : BasePresenter<BiomesView>() {

    fun loadBiome(biome: String) {
        unsubscribeOnDestroy(
            mBiomeManager.getBiome(biome)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                        viewState.showBiome(it)
                        viewState.showBiomeName(mBiomeManager.localizeString(it.biomeName))
                        viewState.showBiomeDesc(mBiomeManager.localizeString(it.biomeDescription))
                        viewState.showBiomeType(mBiomeManager.localizeString(it.biomeType))
                    },
                    onError = {
                        viewState.showMessage(R.string.biome_load_error)
                    }
                )
        )
    }
}
