package me.brisson.todo_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.todo_app.R
import me.brisson.todo_app.ui.theme.TodoappTheme

@Composable
fun AppCheckBox(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onSelect: () -> Unit,
    backgroundColor: Brush = Brush.linearGradient(
        listOf(
            MaterialTheme.colors.secondary,
            MaterialTheme.colors.primary
        )
    )
) {
    val borderWidth = if (selected) 0.dp else 0.5.dp
    val borderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    Box(
        modifier = modifier
            .size(22.dp)
            .clip(CircleShape)
            .border(width = borderWidth, color = borderColor, shape = CircleShape)
            .clickable { onSelect() },
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Icon(
                modifier = modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .clip(CircleShape)
                    .padding(6.dp),
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "check icon",
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAppCheckBox() {
    TodoappTheme {
        var selected by remember { mutableStateOf(true) }
        AppCheckBox(selected = selected, onSelect = { selected = !selected })
    }
}