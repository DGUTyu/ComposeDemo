package com.wxdgut.composedemo.utils

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

/**
 * 包含 Compose 相关辅助方法的实用工具类。
 */
object ComposeUtils {
    /**
     * Object：
     * 使用 object 关键字创建的对象是一个单例对象。这意味着在应用程序的整个生命周期内，只有一个实例存在。
     * object 中的成员方法和属性可以通过对象名直接访问，而不需要创建该对象的实例。
     * 这种方式通常用于创建单例对象或者包含一组相关静态方法和属性的对象。
     *
     * 只需创建一组 Compose 相关的工具方法，而不需要实例化对象，那么使用 object 是合适的。
     * 如果你希望这组工具方法与某个类相关，并希望通过类名调用这些方法，那么你可以使用 companion object。
     * 在 Compose 中，由于通常没有类似于静态方法的概念，因此 object 更为常见。
     */

    /**
     * 将字符串资源 ID 解析为相应的字符串。
     *
     * @param textId 字符串资源 ID。
     * @return 已解析的字符串，如果 [textId] 为 null，则返回空字符串。
     */
    @Composable
    fun getStr(@StringRes textId: Int?): String {
        return if (textId != null) stringResource(id = textId) else ""
    }

    /**
     * 将颜色资源 ID 提供为相应的 [Color]。
     *
     * @param colorId 颜色资源 ID。
     * @return 与 [colorId] 对应的 [Color]。
     */
    @Composable
    fun getColor(@ColorRes colorId: Int): Color {
        return colorResource(id = colorId)
    }

    /**
     * 从字符串表示的颜色值解析出 [Color] 对象。
     *
     * @param colorString 字符串表示的颜色值，例如 "#B7F4EC"。
     * @return [Color] 对象。
     */
    @Composable
    fun parseColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor(colorString))
    }

    /**
     * 将可绘制资源 ID 提供为相应的 [Painter]。
     *
     * @param drawableId 可绘制资源 ID。
     * @return 与 [drawableId] 对应的 [Painter]。
     */
    @Composable
    fun getDrawable(@DrawableRes drawableId: Int): Painter {
        return painterResource(id = drawableId)
    }
}