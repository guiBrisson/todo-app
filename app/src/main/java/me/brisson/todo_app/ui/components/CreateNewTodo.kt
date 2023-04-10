package me.brisson.todo_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.todo_app.domain.model.Todo
import me.brisson.todo_app.ui.theme.TodoappTheme

@Composable
fun CreateNewTodo(
    modifier: Modifier = Modifier,
    onCreate: (todo: Todo) -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    var hasFocus by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 60.dp)
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colors.surface)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AppCheckBox(selected = selected, onSelect = { selected = !selected })
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 4.dp, bottom = 4.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { hasFocus = it.hasFocus },
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
            keyboardActions = KeyboardActions(
                onSend = {
                    onCreate(Todo(content = text, completed = selected))
                    text = ""
                    selected = false
                }
            ),
            cursorBrush = SolidColor(MaterialTheme.colors.onSurface),
            textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (hasFocus) {
                        innerTextField()
                    }
                    if (text.isEmpty()) {
                        Text(
                            modifier = Modifier,
                            text = "Create a new todo...",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.4f),
                        )
                    }
                }

            }
        )

    }
}

@Preview
@Composable
private fun PreviewCreateNewTodo() {
    TodoappTheme {
        CreateNewTodo(onCreate = { })
    }
}