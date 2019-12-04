package com.wimank.craftmaster.tz.app.mvp.models

import android.content.Context
import android.os.Build
import com.wimank.craftmaster.tz.app.rest.responses.LocalizedType
import java.util.*

class LocaleManager(private val mContext: Context) {

    fun localizeString(localizedType: LocalizedType): String {
        return when (getCurrentLocale().language) {
            "ru" -> localizedType.ru
            "uk" -> localizedType.ru
            else -> localizedType.en
        }
    }

    fun getCurrentLocale(): Locale {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mContext.resources.configuration.locales.get(0)
        } else {
            @Suppress("DEPRECATION")
            mContext.resources.configuration.locale
        }
    }

    fun getString(value: Int) = mContext.getString(value)

}
