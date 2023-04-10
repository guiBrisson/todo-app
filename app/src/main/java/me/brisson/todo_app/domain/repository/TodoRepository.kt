package me.brisson.todo_app.domain.repository

import kotlinx.coroutines.flow.Flow
import me.brisson.todo_app.domain.model.Todo

interface TodoRepository {
    suspend fun insertTodo(todo: Todo): Long

    suspend fun updateTodo(todo: Todo)

    fun getAllTodo(): Flow<List<Todo>>

    fun filterTodoByCompleted(completed: Boolean): Flow<List<Todo>>

    suspend fun deleteTodo(vararg todo: Todo)
}