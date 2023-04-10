package me.brisson.todo_app.data.database.repository

import kotlinx.coroutines.flow.Flow
import me.brisson.todo_app.data.database.dao.TodoDao
import me.brisson.todo_app.domain.model.Todo
import me.brisson.todo_app.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoRepository {
    override suspend fun insertTodo(todo: Todo): Long {
        return todoDao.insertTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo) {
        return todoDao.updateTodo(todo)
    }

    override fun getAllTodo(): Flow<List<Todo>> {
        return todoDao.getAllTodo()
    }

    override fun filterTodoByCompleted(completed: Boolean): Flow<List<Todo>> {
        return todoDao.filterTodoByCompleted(completed)
    }

    override suspend fun deleteTodo(vararg todo: Todo) {
        return todoDao.deleteTodo(*todo)
    }
}