package com.wxdgut.composedemo.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import com.wxdgut.composedemo.ui.theme.Pink80
import com.wxdgut.composedemo.utils.SplitScreenContentVertical

@Preview(showBackground = true)
@Composable
fun ConstraintLayoutUse() {
//    SimpleConstraintLayout()
//    GlobalConstraintLayout()
//    DecoupledConstraintLayout1()
//    DecoupledConstraintLayout2()
//    DecoupledConstraintLayout3()
//    DecoupledConstraintLayout4()
    DecoupledConstraintLayout5()
//    LargeConstraintLayout()
}

@Composable
private fun SimpleConstraintLayout() {
    ConstraintLayout {
        // 使用 createRefs() 或 createRefFor() 为 ConstraintLayout 中的每个可组合项创建引用。
        val (button, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 50.dp)
            }
        ) {
            Text("Button")
        }

        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
        })
    }
}

@Composable
fun GlobalConstraintLayout() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        // 定义参考线
        val startGuideline = createGuidelineFromStart(0.02f)
        val endGuideline = createGuidelineFromEnd(0.02f)
        val topGuideline = createGuidelineFromTop(15.dp)
        val bottomGuideline = createGuidelineFromBottom(15.dp)
        // 国际化才使用以下参考线
        val leftLine = createGuidelineFromAbsoluteLeft(10.dp)
        val rightLine = createGuidelineFromAbsoluteRight(0.1f)
        // 定义5个元素别名：头部（header）、侧边栏（sidebar）、内容（content）、底部（footer）和引导线（contentGuideline）
        val (header, sidebar, content, footer, contentGuideline) = createRefs()

        Box(
            modifier = Modifier
                //使用元素别名
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    end.linkTo(endGuideline)
                    // 使用endGuideline时不能加上 start.linkTo(parent.start)
                }
                .size(width = 200.dp, height = 50.dp)
                .background(Color.Blue)
        ) {
            Text(
                text = "Header",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(sidebar) {
                    top.linkTo(header.bottom)
                    start.linkTo(startGuideline)
                    bottom.linkTo(footer.top)
                }
                .size(width = 100.dp, height = 200.dp)
                .background(Color.Green)
        ) {
            Text(
                text = "Sidebar",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(sidebar.top, margin = 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(Color.Yellow)
        ) {
            Text(
                text = "Content",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(footer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottomGuideline)
                }
                .size(width = 200.dp, height = 50.dp)
                .background(Color.Red)
        ) {
            Text(
                text = "Footer",
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // 新增一个使用 createGuidelineFromTop 方法的 Box。
        Box(
            modifier = Modifier
                .constrainAs(contentGuideline) {
                    top.linkTo(topGuideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .background(Color.Magenta)
        ) {
            Text(
                text = "Content Guideline",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun DecoupledConstraintLayout1() {
    // 示例：将约束条件与应用它们的布局分离开来
    // 使用 ConstraintLayout 的构造函数中的 constraintSet 参数来设置约束。
    // 首先，创建了一个引导线 guideline，然后将一个 Box 的右边缘约束到这个引导线上。
    // Box 的约束是通过 .layoutId("box") 和 ConstraintSet 中的 createRefFor("box") 来关联的。
    val nameStr = "box"
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
        constraintSet = ConstraintSet {
            val guideline = createGuidelineFromStart(0.2f)
            val box = createRefFor(nameStr)
            val text = createRefFor("textName")
            constrain(box) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(guideline)
            }
            constrain(text) {
                top.linkTo(box.bottom)
                start.linkTo(box.start)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .layoutId(nameStr)
                .size(width = 100.dp, height = 100.dp)
                .background(Color.Blue)
        )
        Text(
            text = "Below Box",
            modifier = Modifier.layoutId("textName")
        )
    }
}

@Composable
fun DecoupledConstraintLayout2() {
    // 特殊的容器，它允许您根据当前容器的约束条件来构建 UI
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            getDecoupledConstraints2(margin = 16.dp) // Portrait constraints
        } else {
            getDecoupledConstraints2(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text(
                "Text",
                Modifier
                    .layoutId("text")
                    .background(Color.Gray)
            )
        }
    }
}

private fun getDecoupledConstraints2(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}

@Composable
fun EasyConstraintLayout(vararg refs: Pair<String, Dp>, content: @Composable () -> Unit) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
        constraintSet = getDecoupledConstraints3(*refs)
    ) {
        content()
    }
}

@Composable
fun EasyConstraintLayout(vararg refs: Triple<String, Dp, Dp>, content: @Composable () -> Unit) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
        constraintSet = getDecoupledConstraints4(*refs)
    ) {
        content()
    }
}

private fun getDecoupledConstraints3(vararg refs: Pair<String, Dp>): ConstraintSet {
    return ConstraintSet {
        refs.forEachIndexed { index, (ref, margin) ->
            val currentRef = createRefFor(ref)
            if (index == 0) {
                constrain(currentRef) {
                    top.linkTo(parent.top, margin = margin)
                }
            } else {
                val previousRef = createRefFor(refs[index - 1].first)
                constrain(currentRef) {
                    top.linkTo(previousRef.bottom, margin = margin)
                }
            }
        }
    }
}

@Composable
fun DecoupledConstraintLayout3() {
    EasyConstraintLayout(
        Pair("one", 10.dp),
        Pair("two", 20.dp),
        Pair("three", 30.dp)
    ) {
        TextContent()

        Text(
            text = "Hello, Compose!",
            modifier = Modifier
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints)
                    layout(placeable.width, placeable.height) {
                        // 设置外边距，相对于父组件的起始位置（左上角）来定位子组件
                        placeable.placeRelative(
                            20.dp
                                .toPx()
                                .toInt(),
                            10.dp
                                .toPx()
                                .toInt()
                        )
                    }
                }
                .background(Color.LightGray)
        )
    }
}

@Composable
private fun TextContent() {
    Text(
        "Text Blue",
        modifier = Modifier
            .layoutId("two")
            .background(Color.Blue)
    )

    Text(
        "Text Green",
        modifier = Modifier
            .layoutId("one")
            .background(Color.Green)
    )

    Text(
        "Text Yellow",
        modifier = Modifier
            .layoutId("three")
            .background(Color.Yellow)
    )
}

private fun getDecoupledConstraints4(vararg refs: Triple<String, Dp, Dp>): ConstraintSet {
    return ConstraintSet {
        refs.forEachIndexed { index, (ref, marginLeft, marginTop) ->
            val currentRef = createRefFor(ref)
            if (index == 0) {
                constrain(currentRef) {
                    top.linkTo(parent.top, margin = marginTop)
                    start.linkTo(parent.start, margin = marginLeft)
                    end.linkTo(parent.end, margin = marginLeft)
                    width = Dimension.preferredWrapContent
                }
            } else {
                val previousRef = createRefFor(refs[index - 1].first)
                constrain(currentRef) {
                    top.linkTo(previousRef.bottom, margin = marginTop)
                    start.linkTo(parent.start, margin = marginLeft)
                    end.linkTo(parent.end, margin = marginLeft)
                    width = Dimension.preferredWrapContent
                }
            }
        }
    }
}

@Composable
fun DecoupledConstraintLayout4() {
    EasyConstraintLayout(
        Triple("one", 50.dp, 20.dp),
        Triple("two", 100.dp, 30.dp),
        Triple("three", 150.dp, 40.dp)
    ) {
        TextContent()
    }
}

@Composable
fun DecoupledConstraintLayout5() {
    EasyConstraintLayout(
        Triple("one", 20.dp, 0.dp),
        Triple("two", 20.dp, 25.dp),
        Triple("three", 20.dp, 50.dp)
    ) {
        Box(
            modifier = Modifier
                .layoutId("two")
                .background(Color.Blue)
                .height(100.dp)
        ) {
            SplitScreenContentVertical(
                contents = getContentList(),
            )
        }

        Box(
            // 使用了fillMaxWidth() 并且同时设置了 start.linkTo(parent.start, margin = 20.dp)，这可能会导致布局超出屏幕宽度。
            // 因为 fillMaxWidth() 会尽可能地使组件填满其父容器的可用宽度，
            // 而同时设置了 start.linkTo(parent.start, margin = 20.dp) 又将组件的起始位置与父容器的起始位置保持一定的间距。
            modifier = Modifier
                .layoutId("one")
                .fillMaxWidth()
                .background(Color.Green)
                .height(50.dp)
        )

        Box(
            modifier = Modifier
                .layoutId("three")
                .background(Color.Yellow)
                .width(150.dp)
                .height(150.dp)
        )

//        Row(
//            modifier = Modifier
//                .layoutId("three")
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .background(Color.Yellow)
//                    .width(150.dp)
//                    .height(150.dp)
//            )
//        }
    }
}

// 定义一个函数，返回 @Composable 函数列表
fun getContentList(): List<@Composable () -> Unit> {
    return listOf(
        { Button(onClick = { }) { Text("Button") } },
        { Text("Text 2") },
        { Text("Text 3") }
    )
}

@Composable
fun LargeConstraintLayout() {
    //特性：当它的子元素过大时，ConstraintLayout 默认是可以允许子元素超出屏幕范围的
    ConstraintLayout(Modifier.fillMaxHeight()) {
        val text1 = createRef()
        val text2 = createRef()
        val guideline = createGuidelineFromStart(fraction = 0.5f)
        // very 单词有 10 个
        Text(text = "1This text is very1 very2 very3 very4 very5 very6 very7 very8 very9 very10 long",
            modifier = Modifier
                .background(Pink80)
                .constrainAs(text1) {
                    linkTo(start = guideline, end = parent.end)
                }
        )
        // Dimension 的5种属性
        // preferredWrapContent：布局大小是根据内容所设置，并受布局约束的影响。这个例子中对 Text 右边界做了限制，所以使用这个属性可以控制 Text 右边界只能到达父布局右边界，不能超出屏幕；
        // wrapContent：Dimension 的默认值，即布局大小只根据内容所设置，不受约束；
        // fillToConstraints：布局大小将展开填充由布局约束所限制的空间。也就是说，这个属性是先看看布局约束所限制的空间有多大，然后再将该子元素填充到这个有约束的空间中；
        // preferredValue：布局大小是一个固定值，并受布局约束的影响；
        // value：布局大小是一个固定值，不受约束。
        // 此外，Dimension 还可组合设置布局大小，例如：width = Dimension.preferredWrapContent.atLeast(100.dp) 可设置最小布局大小，同样还有 atMost() 可设置最大布局大小等等。
        Text(text = "2This text is very1 very2 very3 very4 very5 very6 very7 very8 very9 very10 long",
            modifier = Modifier
                .background(Pink80)
                .constrainAs(text2) {
                    start.linkTo(guideline)
                    end.linkTo(parent.end)
                    top.linkTo(text1.bottom, 10.dp)
                    width = Dimension.preferredWrapContent
                }
        )
    }
}