package com.example.githubtrending.ui.theme

import androidx.compose.ui.graphics.Color

val BLUE = Color(0xFF8db7ff)
val BLACK = Color(0xFF000000)
val GREEN = Color(0xFF4CAF50)
val YELLOW = Color(0xFFFFEB3B)
val LIGHT_BLUE = Color(0xFF03a9f4)
val TEAL = Color(0xFF009688)
val ORANGE = Color(0xFFff5722)

fun getTeal(): Color {
    return TEAL
}
fun getLightBlue(): Color {
    return LIGHT_BLUE
}

fun getOrange(): Color {
    return ORANGE
}

fun Color.Companion.parse(colorString: String): Color =
    Color(color = android.graphics.Color.parseColor(colorString))


val WHITE = Color(0xFFFFFFFF)
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)