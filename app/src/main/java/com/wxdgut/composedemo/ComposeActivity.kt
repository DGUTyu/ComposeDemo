package com.wxdgut.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.bean.Message
import com.wxdgut.composedemo.ui.theme.Purple40
import com.wxdgut.composedemo.ui.theme.Purple80
import com.wxdgut.composedemo.ui.theme.Shapes
import com.wxdgut.composedemo.utils.ToastUtils

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowPreview()
        }
    }

    //Composable 函数一般用大写开头，为了和普通的函数作为区分
    //Preview注解中 heightDp 和 widthDp 为预览界面的大小，参数值已解释为 dp，因此您无需向它们添加 .dp
    @Preview(showBackground = true)
    @Composable
    fun ShowPreview() {
        ShowModifierText()
    }

    @Composable
    fun ShowText(title: String, subTitle: String) {
        Text("${title}${subTitle}")
    }

    //OptIn(ExperimentalFoundationApi::class) 是 Kotlin 中用于启用实验性API的注解。针对此处的 combinedClickable
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ShowModifierText() {
        //mutableStateOf 是一个 Compose 提供的用于创建可变状态的工具函数
        var clickEnable by remember { mutableStateOf(false) }
        Column {
            val msg = Message("Hello", "Compose")
            //使用系统颜色
            Text(msg.author, modifier = Modifier.background(Color.LightGray).padding(bottom = 5.dp).size(100.dp,20.dp))
            //使用自定义颜色
            Text(msg.body,
                Modifier.run {
                    background(Purple80)
                        .combinedClickable(enabled = clickEnable,
                            onClick = {
                                ToastUtils.showToast(this@ComposeActivity, "onClick")
                            }, onLongClick = {
                                ToastUtils.showToast(this@ComposeActivity, "onLongClick")
                            }, onDoubleClick = {
                                ToastUtils.showToast(this@ComposeActivity, "onDoubleClick")
                            })
                        //p: start, top, end, bottom.
                        .padding(10.dp, 15.dp, 30.dp, 5.dp)
                        .shadow(5.dp, RectangleShape, false)
                }
            )
            Text(
                "hello world", Modifier.background(Purple40).width(60.dp).height(30.dp).clip(Shapes.large)
            )
            Box(
                modifier = Modifier
                    // 让 Box 的大小适应内容
                    .wrapContentSize()
                    .clip(Shapes.large)
                    .clickable(onClick = {
                        clickEnable = !clickEnable;
                        ToastUtils.showToast(this@ComposeActivity, "enabled可不写")
                    })
                    .background(Purple40)
                    .alpha(0.3f)
                    .border(1.dp, Color.Red, Shapes.large)
            ) {
                Text(
                    text = "hello compose",
                    color = Color.White,
                    modifier = Modifier.padding(8.dp).align(Alignment.Center)
                )
            }
        }
    }
}