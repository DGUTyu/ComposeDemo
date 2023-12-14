package com.wxdgut.composedemo.basic

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.R
import com.wxdgut.composedemo.ui.theme.*
import com.wxdgut.composedemo.utils.ImagesWithRedDots
import com.wxdgut.composedemo.utils.SplitScreenContentHorizontal
import com.wxdgut.composedemo.utils.SplitScreenContentVertical

/**
 * Canvas Composable 是官方提供的一个专门用来自定义绘制的独立组件.
 * 这个组件不包含任何子元素，类似于传统View系统中的一个独立View（不是ViewGroup，不包含子View）。
 * 作为一个”独立View“，我们同样也可以通过 Layout Modifier 来定制测量布局过程
 *
 * Canvas参数有两个参数, 类型分别是 Modifier 与 DrawScope.() -> Unit。
 * Modifier 作为该组件的修饰符不难理解， DrawScope.() -> Unit 是一个 reciever 为 DrawScope 类型的 lambda。
 * 那么我们就可以在 lambda 中任意使用 DrawScope 为我们所提供的 API 了。
 *
 * DrawScope 为我们限定了哪些 API:
 * drawLine:绘制一条线
 * drawRect:绘制一个矩形
 * drawImage:绘制一张图片
 * drawRoundRect:绘制一个圆角矩形
 * drawCircle:绘制一个圆
 * drawOval:绘制一个椭圆
 * drawArc:绘制一条弧线
 * drawPath:绘制一条路径
 * drawPoints:绘制一些点
 *
 * DrawScope 会对不同类型的画笔进行缓存的，所以每次绘制时的性能是没有问题的。
 * 可以看CanvasDrawScope的obtainFillPaint源码
 *
 * 打开 Canvas的实现可以发现他其实就是个 Spacer 套壳，真正发挥绘制作用的其实是这个 Modifier.drawBehind() 。
 * drawBehind（画在后面）是修饰在 Spacer 上的，这意味着你所的一切都画在了 Spacer 后面。
 * 由于 Spacer 默认背景是透明的，所以我们所画的就完全展示出来了
 *
 * 自定义绘制，官方为提供了3个 Modifier API，分别是 drawWithContent 、drawBehind 、drawWithCache。
 */
@Preview(showBackground = true)
@Composable
fun ShowCanvasUse() {
    //DrawColorRing()
    DrawBeforeAndDrawBehind()
}

/**
 * 绘制一个半径为 300dp，环宽为 30dp 的圆环，其填充颜色依次为红色、绿色、红色。
 */
@Composable
fun DrawColorRing() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 绘制的圆的直径
        var diameter = 300.dp
        // 绘制的线条宽度
        var ringWidth = 30.dp
        // 颜色列表
        var colorList = listOf(Color.Red, Color.Green, Color.Red)
        Canvas(modifier = Modifier.size(diameter)) {
            // 中心偏移量
            var centerOffset = Offset(diameter.toPx() / 2f, diameter.toPx() / 2f)
            this.drawCircle(
                // 渐变笔刷
                brush = Brush.sweepGradient(colorList, centerOffset),
                // 设置圆的半径为画布大小的一半
                radius = diameter.toPx() / 2f,
                // 使用 Stroke 画笔风格来绘制边框
                style = Stroke(
                    width = ringWidth.toPx()
                )
            )
        }
    }
}

@Composable
fun DrawBeforeAndDrawBehind() {
    var cardShape = RoundedCornerShape(8.dp)
    var drawBeforeModifier = Modifier
        .size(100.dp)
        .drawWithContent {
            drawContent()
            drawCircle(
                Color.Red,
                18.dp.toPx() / 2,
                center = Offset(drawContext.size.width, 0f)
            )
        }
    var drawBehindModifier = Modifier
        .size(100.dp)
        .drawBehind {
            drawCircle(
                Color.Red,
                18.dp.toPx() / 2,
                center = Offset(drawContext.size.width, 0f)
            )
        }
    SplitScreenContentVertical(
        contents = listOf(
            {
                SplitScreenContentHorizontal(
                    contents = listOf({
                        Card(
                            shape = cardShape,
                            modifier = drawBeforeModifier
                        ) {
                            PlacePortraitImg()
                        }
                    }, {
                        Card(
                            shape = cardShape,
                            modifier = drawBehindModifier
                        ) {
                            PlacePortraitImg()
                        }
                    })
                )
            },
            {
                SplitScreenContentHorizontal(
                    contents = listOf({
                        ImagesWithRedDots {
                            PlacePortraitImg()
                        }
                    }, {
                        ImagesWithRedDots(
                            cardShape = CutCornerShape(12.dp),
                            imgSize = 120.dp,
                            dotRadius = 15.dp,
                            dotColor = OrangeRed
                        ) {
                            PlacePortraitImg()
                        }
                    })
                )
            },
            {
                DrawBorder()
            }
        ),
        weights = listOf(1f, 1f, 2f)
    )
}

@Composable
private fun PlacePortraitImg() {
    Image(
        painter = painterResource(id = R.drawable.img_portrait),
        contentDescription = "头像",
        //modifier=Modifier.fillMaxSize()
    )
}

@Composable
fun DrawBorder() {
    var cardSize = 150.dp
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var borderColor by remember { mutableStateOf(Color.Red) }
            Card(
                shape = RoundedCornerShape(0.dp), modifier = Modifier
                    .size(cardSize)
                    // drawWithCache允许在Compose中进行自定义绘制，但只有在其中的 onDrawWithContent 发生变化时才会触发重组（Recompose）
                    .drawWithCache {
                        Log.d("DrawBorder", "此处不会发生 Recompose")
                        // 描述一个矩形的路径，这个矩形作为 Card 的边框
                        var path = Path().apply {
                            // 将绘图位置移动到指定的坐标点。在这里，它是左上角
                            moveTo(0f, 0f)
                            // 从当前绘图位置开始，绘制一条相对于当前位置的水平线。在这里，它会从当前位置向右画出矩形的顶边
                            relativeLineTo(cardSize.toPx(), 0f)
                            // 从当前位置开始，绘制一条相对于当前位置的垂直线。这里会画出矩形的右边
                            relativeLineTo(0f, cardSize.toPx())
                            // 从当前位置开始，绘制一条相对于当前位置的水平线。这里会画出矩形的底边，与顶边相连
                            relativeLineTo(-cardSize.toPx(), 0f)
                            // 从当前位置开始，绘制一条相对于当前位置的垂直线。这里会画出矩形的左边，与右边相连，形成一个封闭的矩形路径
                            relativeLineTo(0f, -cardSize.toPx())
                        }
                        onDrawWithContent {
                            Log.d("DrawBorder", "此处会发生 Recompose")
                            drawContent()
                            drawPath(
                                path = path,
                                color = borderColor,
                                style = Stroke(width = 10f)
                            )
                        }
                    }
            ) {
                PlacePortraitImg()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                borderColor = getMyRandomColorExcludingCurrent(borderColor)
            }) {
                Text("Change Color")
            }
        }
    }
}
