package me.brisson.todo_app.presentation.todo

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.brisson.todo_app.R
import me.brisson.todo_app.domain.model.Todo
import me.brisson.todo_app.ui.components.CreateNewTodo
import me.brisson.todo_app.ui.preview_providers.TodoPreviewProvider
import me.brisson.todo_app.ui.theme.ThemeState
import me.brisson.todo_app.ui.theme.TodoappTheme

@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    TodoScreen(
        modifier = modifier,
        todoUiState = TodoUiState(
            todoList = uiState.todoList,
            loading = uiState.loading,
            error = uiState.error,
        ),
        setDarkTheme = { viewModel.setDarkTheme(it) },
        onCompleted = viewModel::updateTodo,
        onCreateTodo = viewModel::createTodo,
        onDeleteTodo = viewModel::deleteTodo
    )
}

@Composable
internal fun TodoScreen(
    modifier: Modifier = Modifier,
    todoUiState: TodoUiState,
    setDarkTheme: (Boolean) -> Unit,
    onCompleted: (todo: Todo) -> Unit,
    onCreateTodo: (todo: Todo) -> Unit,
    onDeleteTodo: (todo: Todo) -> Unit,
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        val imagePainter = if (isSystemInDarkTheme()) {
            painterResource(id = R.drawable.bg_dark)
        } else {
            painterResource(id = R.drawable.bg_light)
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            painter = imagePainter,
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val iconPainter = if (ThemeState.isDark) {
                    painterResource(id = R.drawable.ic_sun)
                } else {
                    painterResource(id = R.drawable.ic_moon)
                }

                Text(
                    text = "TODO",
                    letterSpacing = 4.sp,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )

                IconButton(
                    onClick = {
                        ThemeState.isDark = !ThemeState.isDark
                        setDarkTheme(!ThemeState.isDark)
                    }
                ) {
                    Icon(
                        painter = iconPainter,
                        contentDescription = "theme toggle button",
                        tint = Color.White
                    )
                }

            }
            CreateNewTodo(onCreate = onCreateTodo)

            TodoList(
                modifier = Modifier.padding(top = 24.dp),
                todoUiState.todoList,
                onCompleted,
                onDeleteTodo
            )
        }
    }
}


@Preview
@Composable
private fun PreviewTodoScreen(@PreviewParameter(TodoPreviewProvider::class) todos: List<Todo>) {
    TodoappTheme {
        TodoScreen(
            todoUiState = TodoUiState(todoList = todos),
            setDarkTheme = { },
            onCompleted = { },
            onCreateTodo = { },
            onDeleteTodo = { },
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDarkTodoScreen(@PreviewParameter(TodoPreviewProvider::class) todos: List<Todo>) {
    TodoappTheme {
        TodoScreen(
            todoUiState = TodoUiState(todoList = todos),
            setDarkTheme = { },
            onCompleted = { },
            onCreateTodo = { },
            onDeleteTodo = { },
        )
    }
}