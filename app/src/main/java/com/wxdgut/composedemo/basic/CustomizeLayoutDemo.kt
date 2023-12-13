package com.wxdgut.composedemo.basic

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.ui.theme.Pink80

/**
 * 在界面树中布置每个节点的过程分为三个步骤。每个节点必须：
 * 1.测量所有子项
 * 2.确定自己的尺寸
 * 3.放置其子项
 *
 * 节点布局的三个步骤：测量子项、确定尺寸、放置子项
 */
@Preview(showBackground = true)
@Composable
fun CustomizeLayoutUse() {
    MyOwnColumn(
        modifier = Modifier
            .background(Pink80)
            .padding(8.dp)
    ) {
        Text("MyOwnColumn")
        Text("places items")
        Text("vertically.")
        Text("We've done it by hand!")
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // 步骤一：
    // Layout最后一个参数的写法可用lambda表达式

//    Layout(
//        modifier = modifier,
//        content = content
//    ) { measurables, constraints ->
//        layout(constraints.maxWidth, constraints.maxWidth) {
//            // Place children
//        }
//    }

    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = { measurables, constraints ->
            // 测量每个子View (切记，每个子元素只允许被测量一次)
            // 最终将每次测量的结果保存到 placeables 这个 List 中
            val placeables = measurables.map {
                it.measure(constraints)
            }
            // 获取所有元素的高度总和
            val wrapHeight = placeables.sumOf {
                it.height
            }
            // 步骤三：布局，设置可允许的布局大小
            // 宽度为modifier约束的宽度，如果没指定modifier.width则默认为其父组件的宽度
            // 高度为所有子元素的高度之和 即wrap_content
            layout(constraints.maxWidth, wrapHeight) {
                var yPosition = 0
                // 步骤四：设置每个组件的位置
                placeables.forEach {
                    // 设置组件的x,y坐标
                    // 具体子元素的布局也与 Layout Modifier 是相同的。
                    // 作为 Column 是需要将子元素进行垂直排列的，所以我们仅需指定每一个子元素的顶部相对位置即可。
                    it.placeRelative(x = 0, y = yPosition)
                    // 计算下一个组件的y坐标
                    yPosition += it.height
                }
            }
        }
    )
}

// 创建一个 firstBaselineToTop 修饰符
@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = Modifier.layout { measurable, constraints ->
    // 1.现在子元素已经完成了测量流程
    val placeable = measurable.measure(constraints)
    // 2.你需要计算当前元素的打算并对当前元素的宽度与高度进行指定，将子元素的布局流程写入在 layout(width, height) 的 lambda 参数中
    // 确保测量的元素确实具有一个有效的，如果没有明确指定基线，那么测量过程中会使用 AlignmentLine.Unspecified
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    // 通过placeable对象获取已测量元素的第一个FirstBaseline的位置，FirstBaseline是一个键，它用于从placeable对象中获取有关基线位置的信息
    val firstBaseline = placeable[FirstBaseline]
    // roundToPx() 是一个将 Dp 转换为整数像素值的函数，返回Int。toPx()返回Float。类似的还有toSp，toDp等
    // 计算要移动到的顶部基线的位置：
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // 3.通过使用 placeable.placeRelative(x, y) 来完成子元素的布局流程，这是必要的。
        // placeRelative 会根据当前 layoutDirection 自动调整子元素的位置。
        // 当前示例中，当前子元素的横向坐标相对当前元素为零，而纵向坐标则为 Text 组件顶部到文本顶部的距离。
        placeable.placeRelative(0, placeableY)
    }
}

@Preview(showBackground = true)
@Composable
fun TextWithPaddingToBaselinePreview() {
    Text("Hi there!", Modifier.firstBaselineToTop(24.dp))
}

@Preview(showBackground = true)
@Composable
fun TextWithNormalPaddingPreview() {
    Text("Hi there!", Modifier.padding(top = 24.dp))
}
