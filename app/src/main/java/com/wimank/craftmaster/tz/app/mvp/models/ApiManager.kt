package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.rest.api.*

data class ApiManager(
    val imageApi: ImageApi,
    val recipesApi: RecipesApi,
    val mobsApi: MobsApi,
    val devicesApi: DevicesApi,
    val achievementsApi: AchievementsApi,
    val biomesApi: BiomesApi,
    val brewingApi: BrewingApi,
    val addInfoApi: AddInfoApi,
    val dbVersApi: DbVersApi
)
