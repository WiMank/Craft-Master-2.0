package com.wimank.craftmaster.tz.app.utils

import android.content.Context
import android.os.Build
import com.wimank.craftmaster.tz.app.room.LocalizedType
import java.util.*

fun getCurrentLocale(context: Context): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales.get(0)
    } else {
        @Suppress("DEPRECATION")
        context.resources.configuration.locale
    }
}

fun localizeString(context: Context, localizedType: LocalizedType): String {
    return when (getCurrentLocale(context).language) {
        "ru" -> localizedType.getRuLocalization()
        "uk" -> localizedType.getRuLocalization()
        else -> localizedType.getEnLocalization()
    }
}