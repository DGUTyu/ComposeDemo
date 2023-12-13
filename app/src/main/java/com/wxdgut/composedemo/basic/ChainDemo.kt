package com.wxdgut.composedemo.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.wxdgut.composedemo.ui.theme.Pink80
import com.wxdgut.composedemo.ui.theme.Purple40
import com.wxdgut.composedemo.ui.theme.Purple80

@Preview(showBackground = true)
@Composable
fun ChainUse() {
    // Chain 链，与 xml 中的用法一样，就是将一系列子元素按顺序打包成一行或一列。
    // 官方将这个 api 标记为可以改进的状态，可能后续会发生变化。api 只有两个，创建横向和纵向的链

    // ChainStyle.Spread: 将链中的元素均匀分布，首尾元素分别固定在链的两端。
    // （该方向下每一个元素的外边距都有且相等；该方向元素设置的外部边距无效）

    // ChainStyle.SpreadInside: 链式布局的样式，首尾元素分别固定在链的两端，其余元素均匀分布在首尾元素之间。
    //（该方向下，首尾元素无外边距，内部每一个元素的外边距都有且相等；该方向元素设置的外部边距无效）

    // ChainStyle.Packed: 将链中的元素紧凑排列并放置在可用空间的中心位置（偏移量为 0.5f）。
    //（该方向下，每一个元素无内边距，整体居中；该方向元素设置的外部边距无效）
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (box1, box2, box3) = createRefs()
        createHorizontalChain(box1, box2, box3, chainStyle = ChainStyle.Spread)
        createVerticalChain(box1, box2, box3, chainStyle = ChainStyle.Spread)
        val (box4, box5, box6) = createRefs()
        //createHorizontalChain(box4, box5, box6, chainStyle = ChainStyle.SpreadInside)
        createVerticalChain(box4, box5, box6, chainStyle = ChainStyle.SpreadInside)
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .constrainAs(box1) {})
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Green)
            .constrainAs(box2) {})
        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .constrainAs(box3) {})

        Box(modifier = Modifier
            .size(100.dp)
            .background(Purple80)
            .constrainAs(box4) { start.linkTo(parent.start, 10.dp) })
        Box(modifier = Modifier
            .size(100.dp)
            .background(Pink80)
            .constrainAs(box5) {})
        Box(modifier = Modifier
            .size(100.dp)
            .background(Purple40)
            .constrainAs(box6) {})
    }
}