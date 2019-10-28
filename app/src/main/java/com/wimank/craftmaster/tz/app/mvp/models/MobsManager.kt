package com.wimank.craftmaster.tz.app.mvp.models

import android.content.Context
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import com.wimank.craftmaster.tz.app.room.CraftMasterDataBase
import com.wimank.craftmaster.tz.app.utils.getCurrentLocale

class MobsManager(
    private val mContext: Context,
    private val mCraftMasterDataBase: CraftMasterDataBase
) {

    fun getMob(mob: String) = mCraftMasterDataBase.mobsDao().getMob(mob)

    fun localizeString(localizedType: LocalizedType): String {
        return when (getCurrentLocale(mContext).language) {
            "ru" -> localizedType.ru
            "uk" -> localizedType.ru
            else -> localizedType.en
        }
    }
}
