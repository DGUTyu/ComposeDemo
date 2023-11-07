package com.wxdgut.composedemo.diff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wxdgut.composedemo.themes.MyApplicationTheme

/**
 * 声明式UI中，开发人员描述当前的UI状态，数据更新后UI的刷新交给Compose框架
 *
 * MyApplicationTheme 和 Surface 是 Jetpack Compose 中用于构建用户界面的两个重要组件。
 *
 * MyApplicationTheme：
 *
 * MyApplicationTheme 是一个自定义的 Composable 函数，通常用于设置应用程序的整体外观和样式。
 * 它可以包含一些全局的主题配置，如颜色、字体、形状等。
 * 在 Compose 中，主题可以在整个应用程序中统一管理样式，使得 UI 的风格一致性更容易维护。
 *
 * 例如，您可以在 MyApplicationTheme 中设置应用程序的颜色方案、字体样式、形状等，然后在整个应用程序中使用这个主题来保持一致的外观。
 *
 * Surface：
 *
 * Surface 是一个用于呈现 UI 元素的容器。
 * 它类似于 Android 中的布局容器，但在 Compose 中，Surface 通常用于包裹其他组件，并且可以用于设置背景颜色、阴影等样式。
 *
 * 例如，您可以在 Surface 中放置一个或多个 Composables，同时为 Surface 设置特定的背景颜色，以及可能的阴影效果。
 *
 * Surface(color = Color.Blue) {
 *     // 在这里放置其他 Composables
 * }
 *
 * 总的来说，MyApplicationTheme 和 Surface 是 Compose 中用于构建和定制用户界面的重要工具。
 * 前者用于定义全局样式和主题，后者用于包裹和呈现具体的 UI 元素。通过合理使用这两者，可以更容易地实现 UI 的定制和样式管理。
 */
class DeclarativeUIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.Blue) {
                    // 在这里放置其他 Composables
                    Timer()
                }
            }
        }
    }
}

@Composable
fun Timer() {
    var count by remember { mutableStateOf(0) }

    Text(
        text = "count:${count}",
        modifier = Modifier.clickable {
            count++
        },
        style = typography.headlineMedium
    )
}