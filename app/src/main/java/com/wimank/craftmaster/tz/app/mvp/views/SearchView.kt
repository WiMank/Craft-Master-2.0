package com.wimank.craftmaster.tz.app.mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.wimank.craftmaster.tz.app.room.entity.DescriptionEntity

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : MvpView {

    fun showList(list: List<DescriptionEntity>)

}
