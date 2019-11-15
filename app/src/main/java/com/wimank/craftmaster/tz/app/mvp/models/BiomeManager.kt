package com.wimank.craftmaster.tz.app.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.utils.getCurrentLocale

class BiomeManager(
    private val mContext: Context,
    private val mCraftMasterDataBase: CraftMasterDataBase
) {

    fun getBiome(biome: String) = mCraftMasterDataBase.biomesDao().getBiome(biome)

    fun localizeString(localizedType: LocalizedType): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> localizedType.ru
            "uk" -> localizedType.ru
            else -> localizedType.en
        }
    }
}
