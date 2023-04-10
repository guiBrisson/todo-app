package me.brisson.todo_app.presentation.todo

import me.brisson.todo_app.domain.model.Todo

data class TodoUiState(
    val loading: Boolean = true,
    val todoList: List<Todo> = emptyList(),
    val error: Throwable? = null,
)
