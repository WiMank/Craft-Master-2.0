package com.wimank.craftmaster.tz.app.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.room.entity.BiomesEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface BiomesView : MvpView {

    fun showBiome(biomeItem: BiomesEntity)

    fun showBiomeName(biomeName: String)

    fun showBiomeDesc(biomeDesc: String)

    fun showBiomeType(biomeType: String)

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: Int)

}
