package me.brisson.todo_app.domain.repository

interface SharedPrefs {
    fun setDarkTheme(value: Boolean)

    fun isDarkTheme(): Boolean?
}