package com.wxdgut.composedemo.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Red = Color(0xFFF53F3F)
val Red80 = Red.copy(alpha = 0.8f)
val OrangeRed = Color(0xFFF77234)
val Orange = Color(0xFFFF7D00)
val Gold = Color(0xFFF7BA1E)
val Yellow = Color(0xFFFADC19)
val Lime = Color(0xFF9FDB1D)
val Green = Color(0xFF00B42A)
val Cyan = Color(0xFF14C9C9)
val Blue = Color(0xFF3491FA)
val ArcoBlue = Color(0xFF165DFF)
val Purple = Color(0xFF722ED1)
val PinkPurple = Color(0xFFD91AD9)
val Magenta = Color(0xFFF5319D)
val Grey = Color(0xFF86909C)

/**
 * 返回颜色List
 * includeGrey 参数提供默认值，这样调用时如果不传参数，默认值会被使用。
 */
fun getMyColorList(includeGrey: Boolean = false): List<Color> {
    val colors = listOf(
        Red, OrangeRed, Orange, Gold, Yellow,
        Lime, Green, Cyan, Blue, ArcoBlue,
        Purple, PinkPurple, Magenta
    )
    return if (includeGrey) {
        colors + Grey
    } else {
        colors
    }
}

/**
 * 返回颜色List中的任意一种颜色，不能是当前颜色
 */
fun getMyRandomColorExcludingCurrent(currentColor: Color, includeGrey: Boolean = false): Color {
    val colors = getMyColorList(includeGrey)
    val filteredColors = colors.filter { it != currentColor }
    return filteredColors.random()
}