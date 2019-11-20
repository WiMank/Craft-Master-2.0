package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase

class BiomeManager(
    private val mLocaleManager: LocaleManager,
    private val mCraftMasterDataBase: CraftMasterDataBase
) {

    fun getBiome(biome: String) = mCraftMasterDataBase.biomesDao().getBiome(biome)

    fun localizeString(localizedType: LocalizedType): String {
        return mLocaleManager.localizeString(localizedType)
    }
}
