package me.brisson.todo_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.brisson.todo_app.data.database.dao.TodoDao
import me.brisson.todo_app.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}