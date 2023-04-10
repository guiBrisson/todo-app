package me.brisson.todo_app.presentation.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.brisson.todo_app.domain.model.Todo
import me.brisson.todo_app.domain.repository.SharedPrefs
import me.brisson.todo_app.domain.repository.TodoRepository
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
    private val sharedPrefs: SharedPrefs,
) : ViewModel() {

    private var _allTodos = todoRepository.getAllTodo()
        .catch { t -> _uiState.update { it.copy(loading = false, error = t) } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = combine(_allTodos, _uiState) { todos, state ->
        state.copy(loading = false, todoList = todos)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _uiState.value)

    fun setDarkTheme(isDarkTheme: Boolean) {
        sharedPrefs.setDarkTheme(isDarkTheme)
    }

    fun createTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.insertTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todo)
        }
    }

    fun deleteTodo(vararg todo: Todo) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(*todo)
        }
    }
}