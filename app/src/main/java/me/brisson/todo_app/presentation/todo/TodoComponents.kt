package me.brisson.todo_app.presentation.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.brisson.todo_app.R
import me.brisson.todo_app.domain.model.Todo
import me.brisson.todo_app.ui.components.AppCheckBox
import me.brisson.todo_app.ui.preview_providers.TodoPreviewProvider
import me.brisson.todo_app.ui.theme.TodoappTheme
import me.brisson.todo_app.ui.theme.josefinSans

@Composable
fun TodoList(
    modifier: Modifier = Modifier,
    todos: List<Todo>,
    onCompleted: (todo: Todo) -> Unit,
    onDeleteTodo: (todo: Todo) -> Unit
) {

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .shadow(6.dp)
    ) {
        itemsIndexed(todos) { index, todo ->
            val shape = when (index) {
                0 -> {
                    RoundedCornerShape(topEnd = 6.dp, topStart = 6.dp)
                }
                else -> {
                    RoundedCornerShape(0.dp)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 64.dp)
                    .height(IntrinsicSize.Min)
                    .clip(shape)
                    .background(MaterialTheme.colors.surface)
                    .clickable { onCompleted(todo.copy(completed = !todo.completed)) },
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val textDecoration = if (todo.completed) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                    val textColor = if (todo.completed) {
                        MaterialTheme.colors.onSurface.copy(0.2f)
                    } else {
                        MaterialTheme.colors.onSurface.copy(0.6f)
                    }

                    AppCheckBox(
                        selected = todo.completed,
                        onSelect = { onCompleted(todo.copy(completed = !todo.completed)) }
                    )

                    Text(
                        modifier = Modifier
                            .padding(12.dp)
                            .weight(1f),
                        text = todo.content,
                        color = textColor,
                        textDecoration = textDecoration,
                        style = MaterialTheme.typography.body1,
                    )
                    Icon(
                        modifier = Modifier
                            .size(14.dp)
                            .clickable { onDeleteTodo(todo) },
                        painter = painterResource(id = R.drawable.ic_cross),
                        contentDescription = "",
                        tint = Color(0xff494C6B)
                    )
                }
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 0.5.dp,
                    color = Color(0xff494C6B)
                )
            }
        }

        if (todos.isNotEmpty()) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomEnd = 6.dp, bottomStart = 6.dp))
                        .background(MaterialTheme.colors.surface)
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val completedItems = todos.filter { it.completed }
                    val textStyle = TextStyle(
                        fontFamily = josefinSans,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Text(text = "${completedItems.size} item(s) completed", style = textStyle)
                    Text(
                        modifier = Modifier.clickable {
                            completedItems.forEach { onDeleteTodo(it) }
                        },
                        text = "Clear Completed",
                        style = textStyle
                    )
                }

            }

        }
    }
}

@Preview
@Composable
private fun PreviewTodoList(@PreviewParameter(TodoPreviewProvider::class) todos: List<Todo>) {
    TodoappTheme {
        TodoList(todos = todos, onCompleted = { }, onDeleteTodo = { })
    }
}