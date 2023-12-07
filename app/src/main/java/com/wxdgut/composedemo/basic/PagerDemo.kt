package com.wxdgut.composedemo.basic

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.ui.theme.Pink80
import com.wxdgut.composedemo.ui.theme.Purple80
import com.wxdgut.composedemo.utils.TextCenteredInBox
import com.wxdgut.composedemo.utils.SplitScreenContentVertical
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PagerUse() {
    val pagerStateH = rememberPagerState(2)
    val pagerStateV = rememberPagerState()
    SplitScreenContentVertical(
        contents = listOf(
            {
                HorizontalPagerIndicatorUse(pagerStateH, 10)
            },
            {
                VerticalPagerIndicatorUse(pagerStateV, 15)
            }
        )
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun PagerUse2() {
    val pagerStateH = rememberPagerState()
    val pagerStateV = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    SplitScreenContentVertical(
        contents = listOf(
            {
                HorizontalPagerScrollUse(pagerStateH)
            },
            {
                VerticalPagerScrollUse(pagerStateV)
            },
            {
                ClickBtnToScroll(coroutineScope, pagerStateH, 5)
                ClickBtnToScroll(coroutineScope, pagerStateV, 9)
            }
        ),
        weights = listOf(4f, 4f, 2f)
    )
}

@Composable
private fun PagerUse1() {
    Column {
        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color.Red)
        ) {
            //默认元素顶部居中显示，只有内部区域可滑动
            HorizontalPagerUse()
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .background(Color.Blue)
        ) {
            //默认元素垂直居中显示，全部区域可滑动
            VerticalPagerUse()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerUse() {
    HorizontalPagerUse(true)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerUse(wrap: Boolean) {
    HorizontalPager(pageCount = 10) { page ->
        TextCenteredInBox(
            text = "HorizontalPager: $page",
            modifier = Modifier.background(Purple80),
            wrapContent = wrap
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPagerUse() {
    VerticalPagerUse(true)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPagerUse(wrap: Boolean) {
    VerticalPager(pageCount = 10) { page ->
        TextCenteredInBox(
            text = "VerticalPager: $page",
            modifier = Modifier.background(Pink80),
            wrapContent = wrap
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ClickBtnToScroll(
    coroutineScope: CoroutineScope,
    pagerState: PagerState,
    toIndex: Int
) {
    Button(
        onClick = {
            coroutineScope.launch {
                // Call scroll to on pagerState
                // pagerState.animateScrollToPage(5)
                pagerState.scrollToPage(toIndex)
            }
        }
    ) {
        Text("Jump to Page $toIndex")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerScrollUse(pagerState: PagerState) {
    HorizontalPager(pageCount = 10, state = pagerState) { page ->
        TextCenteredInBox(
            text = "HorizontalPager: $page",
            modifier = Modifier.background(Purple80)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPagerScrollUse(pagerState: PagerState) {
    VerticalPager(pageCount = 10, state = pagerState) { page ->
        TextCenteredInBox(
            text = "VerticalPager: $page",
            modifier = Modifier.background(Pink80)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerIndicatorUse(pagerState: PagerState, count: Int) {
    Column {
        HorizontalPager(
            pageCount = count,
            state = pagerState,
            //默认铺满布局，如果要加指示器，则要设置weight
            modifier = Modifier.weight(1f)
        ) { page ->
            Column {
                TextCenteredInBox(
                    text = "HorizontalPager: $page",
                    modifier = Modifier.background(Purple80),
                )
            }
        }
        // 圆形指示器，使用 pagerState.currentPage 重复显示圆形数量并根据是否选择了页面来更改圆形颜色
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(count) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPagerIndicatorUse(pagerState: PagerState, count: Int) {
    VerticalPager(
        pageCount = count,
        state = pagerState,
        pageSize = createPagesPerViewport(2)
    )
    { page ->
        Card(
            Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    // We animate the alpha, between 50% and 100%
                    alpha = myLerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    //alpha=0.5f
                }
        ) {
            // Card content
            TextCenteredInBox(
                text = "HorizontalPager: $page",
                modifier = Modifier.background(Pink80),
            )
        }
    }
}

// 自定义的插值函数,在给定范围内执行线性插值操作
// 接受起始值 start、结束值 stop 和插值因子 fraction 作为参数，然后执行线性插值计算。
fun myLerp(start: Float, stop: Float, fraction: Float): Float {
    return start + fraction * (stop - start)
}

@ExperimentalFoundationApi
private val threePagesPerViewport = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return (availableSpace - 2 * pageSpacing) / 3
    }
}

@ExperimentalFoundationApi
fun createPagesPerViewport(equalParts: Int): PageSize {
    return object : PageSize {
        override fun Density.calculateMainAxisPageSize(
            availableSpace: Int,
            pageSpacing: Int
        ): Int {
            return (availableSpace - (equalParts - 1) * pageSpacing) / equalParts
        }
    }
}