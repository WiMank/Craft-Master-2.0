package com.wimank.craftmaster.tz.app.rest.api

import com.wimank.craftmaster.tz.app.rest.responses.AchievementResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AchievementsApi {

    @GET("/achievements")
    fun getAchievements(): Single<AchievementResponse>

}
