package me.brisson.todo_app.ui.preview_providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.brisson.todo_app.domain.model.Todo

class TodoPreviewProvider : PreviewParameterProvider<List<Todo>> {
    override val values = sequenceOf(
        listOf(
            Todo(content = "Jog around the park"),
            Todo(content = "10 minutes meditation"),
            Todo(content = "Read for 1 hour"),
            Todo(content = "Pick up groceries"),
            Todo(content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."),
        )
    )
}