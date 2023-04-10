package me.brisson.todo_app.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.brisson.todo_app.domain.model.Todo

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo): Long

    @Update
    suspend fun updateTodo(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM Todo WHERE completed = :completed")
    fun filterTodoByCompleted(completed: Boolean): Flow<List<Todo>>

    @Delete
    suspend fun deleteTodo(vararg todo: Todo)
}