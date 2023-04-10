package me.brisson.todo_app.data.database.repository

import android.content.Context
import me.brisson.todo_app.domain.repository.SharedPrefs

class SharedPrefsImpl(context: Context): SharedPrefs {
    private val sharedPrefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun setDarkTheme(value: Boolean) {
        sharedPrefs.edit().putBoolean(SYSTEM_DARK_NAME, value).apply()
    }

    override fun isDarkTheme(): Boolean? {
        return if (!sharedPrefs.contains(SYSTEM_DARK_NAME)) {
            null
        } else {
            sharedPrefs.getBoolean(SYSTEM_DARK_NAME, false)
        }
    }

    companion object {
        private const val PREF_NAME = "app_pref"
        private const val SYSTEM_DARK_NAME = "system_dark"
    }
}