package me.brisson.todo_app.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.brisson.todo_app.R

private val DarkColorPalette = darkColors(
    primary = Color(0xFFaa79ed),
    secondary = Color(0xFF67bffe),
    background = Color(0xFF181824),
    onBackground = Color(0xFFfafafa),
    surface = Color(0xFF25273c),
    onSurface = Color(0xFFFFFFFF),
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFaa79ed),
    secondary = Color(0xFF67bffe),
    background = Color(0xFFfafafa),
    onBackground = Color(0xFF181824),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF25273c),
)

val josefinSans = FontFamily(
    Font(R.font.josefin_sans_light, weight = FontWeight.Light),
    Font(R.font.josefin_sans_regular, weight = FontWeight.Normal),
    Font(R.font.josefin_sans_medium, weight = FontWeight.Medium),
    Font(R.font.josefin_sans_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.josefin_sans_bold, weight = FontWeight.Bold),
)

private val typography = Typography(
    defaultFontFamily = josefinSans,
    body1 = TextStyle(
        fontSize = 16.sp,
    ),
)

@Composable
fun TodoappTheme(content: @Composable () -> Unit) {
    val colors = if (ThemeState.isDark) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}

object ThemeState {
    var isDark by mutableStateOf(false)
}