package me.brisson.todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import dagger.hilt.android.AndroidEntryPoint
import me.brisson.todo_app.domain.repository.SharedPrefs
import me.brisson.todo_app.presentation.todo.TodoScreen
import me.brisson.todo_app.ui.theme.ThemeState
import me.brisson.todo_app.ui.theme.TodoappTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var sharedPrefs: SharedPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            (sharedPrefs.isDarkTheme() ?: isSystemInDarkTheme()).let { ThemeState.isDark = it }
            TodoappTheme {
                TodoScreen()
            }
        }
    }
}

