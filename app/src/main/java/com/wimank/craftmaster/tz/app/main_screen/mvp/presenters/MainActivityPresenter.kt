package com.wimank.craftmaster.tz.app.main_screen.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.wimank.craftmaster.tz.app.categories_screen.room.Category
import com.wimank.craftmaster.tz.app.categories_screen.room.CategoryEntity
import com.wimank.craftmaster.tz.app.main_screen.mvp.views.MainActivityView
import com.wimank.craftmaster.tz.common.mvp.BasePresenter

@InjectViewState
class MainActivityPresenter : BasePresenter<MainActivityView>() {

    fun checkCategoryAndGroup(categoryEntity: CategoryEntity) {
        when (categoryEntity.category) {
            Category(
                en = "Blocks and Items",
                ru = "Блоки и предметы"
            ) -> viewState.openBlocksandItemsCategory(categoryEntity)

            Category(en = "Biomes", ru = "Биомы") -> viewState.openBiomesCategory()

            Category(en = "Mobs", ru = "Мобы") -> viewState.openMobsCategory()

            Category(en = "Achievements", ru = "Достижения") -> viewState.openAchievementsCategory(
                categoryEntity
            )
            Category(en = "Potions", ru = "Зелья") -> viewState.openPotionsCategory()

            Category(en = "Comands", ru = "Команды") -> viewState.openComandsCategory()
        }
    }
}
