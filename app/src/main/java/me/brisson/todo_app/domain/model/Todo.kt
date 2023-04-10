package me.brisson.todo_app.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "todo_id")
    val id: Int = 0,
    val content: String,
    val completed: Boolean = false
)
