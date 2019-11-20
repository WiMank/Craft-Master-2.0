package com.wimank.craftmaster.tz.app.mvp.models

import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase

class MobsManager(
    private val mLocaleManager: LocaleManager,
    private val mCraftMasterDataBase: CraftMasterDataBase
) {

    fun getMob(mob: String) = mCraftMasterDataBase.mobsDao().getMob(mob)

    fun localizeString(localizedType: LocalizedType): String {
        return mLocaleManager.localizeString(localizedType)
    }
}
