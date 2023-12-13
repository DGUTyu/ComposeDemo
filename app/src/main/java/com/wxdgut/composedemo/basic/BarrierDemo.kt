package com.wxdgut.composedemo.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wxdgut.composedemo.ui.theme.Pink80
import com.wxdgut.composedemo.ui.theme.Purple80

@Preview(showBackground = true)
@Composable
fun BarrierUse() {
    //BarrierUse1()
    BarrierUse2()
}

@Composable
private fun BarrierUse1() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
        ) {
            Text("Button1")
        }

        Text(text = "Text中心在button1的右边界", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom)
            // 将 Text 的中心摆放在 button1 右边界的位置
            centerAround(button1.end)
        })

        // 设置一个 button1 和 text 右边的一个栅栏，将两者放在栅栏的左侧
        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 20.dp)
                // 将 button2 放在栅栏的右侧
                start.linkTo(barrier)
            }
        ) {
            Text(text = "Button2")
        }
    }
}

@Composable
fun BarrierUse2() {
    // Barrier要在ConstraintLayout中使用
    ConstraintLayout {
        val (btn, helloTv, startTv, topTv, endTv, bottomTv, leftTv, rightTv) = createRefs()
        val startGuideline = createGuidelineFromStart(0.5f)
        val startBarrier = createStartBarrier(helloTv)
        val topBarrier = createTopBarrier(helloTv)
        val endBarrier = createEndBarrier(helloTv)
        val bottomBarrier = createBottomBarrier(helloTv)
        val leftBarrier = createAbsoluteLeftBarrier(btn)
        val rightBarrier = createAbsoluteRightBarrier(btn)
        Text(text = "Hello Compose", Modifier.constrainAs(helloTv) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(startGuideline)
        })
        Text(text = "topBarrier", Modifier.constrainAs(topTv) {
            top.linkTo(bottomBarrier)
        })
        Text(text = "bottomBarrier", Modifier.constrainAs(bottomTv) {
            bottom.linkTo(topBarrier)
            // 将 Text 的中心摆放在 button1 右边界的位置
            centerAround(btn.end)
        })
        Text(text = "startBarrier", modifier = Modifier
            .width(120.dp)
            .background(Pink80)
            .constrainAs(startTv) {
                top.linkTo(helloTv.bottom)
                end.linkTo(startBarrier)
            })
        Text(text = "endBarrier", modifier = Modifier
            .width(120.dp)
            .background(Purple80)
            .constrainAs(endTv) {
                top.linkTo(helloTv.bottom)
                start.linkTo(endBarrier)
            })
        Button(
            onClick = {},
            modifier = Modifier.constrainAs(btn) {
                top.linkTo(helloTv.top, margin = 50.dp)
                centerHorizontallyTo(startTv)
            }
        ) {
            Text(text = "Button")
        }
        // 下面两个是用于国际化适配，因为有些语言是从右到左排列的
        Text(text = "leftBarrier", modifier = Modifier
            .width(100.dp)
            .background(Pink80)
            .constrainAs(leftTv) {
                top.linkTo(btn.bottom)
                end.linkTo(leftBarrier)
            }
        )
        Text(text = "rightBarrier", modifier = Modifier
            .width(100.dp)
            .background(Purple80)
            .constrainAs(rightTv) {
                top.linkTo(btn.bottom)
                start.linkTo(rightBarrier)
                centerVerticallyTo(btn)
            })
    }
}