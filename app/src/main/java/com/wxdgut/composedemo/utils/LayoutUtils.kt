package com.wxdgut.composedemo.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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