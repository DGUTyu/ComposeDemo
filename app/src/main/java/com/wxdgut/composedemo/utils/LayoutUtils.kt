package com.wxdgut.composedemo.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * 水平居中
 */
@Composable
fun HorizontallyCenteredRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        content = content
    )
}

/**
 * 垂直居中
 */
@Composable
fun VerticallyCenteredRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * 中心居中
 */
@Composable
fun CenteredRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}

/**
 * Row的使用
 */
@Composable
fun SampleCenteredRowUsage() {
    Column(
        // 外部定义宽度，否则宽度铺满
        modifier = Modifier.width(300.dp)
    ) {
        HorizontallyCenteredRow(
            // 内部定义高度，否则高度包裹
            modifier = Modifier
                .background(Color.Red)
                .height(100.dp)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("确认")
            }
        }

        VerticallyCenteredRow(
            modifier = Modifier.background(Color.Blue)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("确认")
            }
        }

        CenteredRow(
            modifier = Modifier.background(Color.Green)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("确认")
            }
        }
    }
}

/**
 * 顶部居中
 */
@Composable
fun TopCenteredColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        content = content
    )
}

/**
 * 中心居中
 */
@Composable
fun CenteredColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = content
    )
}

/**
 * 底部居中
 */
@Composable
fun BottomCenteredColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        content = content
    )
}

/**
 * Column的使用
 */
@Composable
fun SampleCenteredColumnUsage() {
    Row(
        // 外部定义高度，否则高度铺满
        modifier = Modifier.height(200.dp)
    ) {
        TopCenteredColumn(
            // 内部定义宽度，否则宽度包裹
            modifier = Modifier
                .background(Color.Red)
                .width(100.dp)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("确认")
            }
        }

        CenteredColumn(
            modifier = Modifier.background(Color.Blue)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("确认")
            }
        }

        BottomCenteredColumn(
            modifier = Modifier.background(Color.Green)
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text("确认")
            }
        }
    }
}


/**
 * 将内容垂直方向按比例分割屏幕
 *
 * 利用 Kotlin 的默认参数实现，如果调用时没有传递weights或fillMaxWidth参数，它们将自动使用默认值
 *
 * run是 Kotlin 标准库中的一个函数，它允许你在对象上执行代码块，并返回最后一个表达式的结果
 * 当你调用 run 时，它会将当前对象作为闭包的接收者（即this），然后你可以在这个闭包内部执行操作，比如调用对象的方法、访问属性等。
 * run函数最终会返回闭包中最后一个表达式的结果。
 */
@Composable
fun SplitScreenContentVertical(
    contents: List<@Composable () -> Unit>,
    weights: List<Float> = listOf(1f, 1f, 1f),
    fillMaxWidth: Boolean = true
) {
    Column(modifier = Modifier.fillMaxSize()) {
        contents.forEachIndexed { index, content ->
            Row(
                modifier = Modifier
                    .weight(weights.getOrNull(index) ?: 1f)
                    .run { if (fillMaxWidth) fillMaxWidth() else this },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                content()
            }
        }
    }
}

/**
 * 将内容水平方向按比例分割屏幕
 */
@Composable
fun SplitScreenContentHorizontal(
    contents: List<@Composable () -> Unit>,
    weights: List<Float> = listOf(1f, 1f, 1f),
    fillMaxHeight: Boolean = true
) {
    Row(modifier = Modifier.fillMaxSize()) {
        contents.forEachIndexed { index, content ->
            Column(
                modifier = Modifier
                    .weight(weights.getOrNull(index) ?: 1f)
                    .run { if (fillMaxHeight) fillMaxHeight() else this },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content()
            }
        }
    }
}

/**
 * 将文本放置在一个具有文本属性的框中，并根据参数设置进行居中显示或自适应包裹内容。
 *
 * @param text 要显示的字符串。
 * @param modifier 应用于框的修饰符。
 * @param textAlign 文本的水平对齐方式。
 * @param style 文本的样式。
 * @param wrapContent 是否根据内容自适应包裹。
 */
@Composable
fun TextCenteredInBox(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    style: TextStyle = TextStyle.Default,
    wrapContent: Boolean = false
) {
    val finalModifier = if (wrapContent) {
        modifier.wrapContentSize(Alignment.Center)
    } else {
        modifier.fillMaxSize()
    }

    Box(
        modifier = finalModifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            textAlign = textAlign,
            style = style
        )
    }
}