package com.wxdgut.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    @Preview(showBackground = true)
    @Composable
    fun ShowPreview() {
        ShowModifierText()
    }

    @Composable
    fun ShowText(title: String, subTitle: String) {
        Text("${title}${subTitle}")
    }
    @Composable
    fun ShowModifierText() {
        Column {
            val msg = Message("Hello", "Compose")
            //使用系统颜色
            Text(msg.author, modifier = Modifier.background(Color.LightGray).padding(bottom = 5.dp).size(100.dp,20.dp))
            //使用自定义颜色
            Text(msg.body,
                Modifier
                    .background(Purple80)
                    .clickable {
                        ToastUtils.showToast(this@ComposeActivity, msg.body)
                    }
                    //p: start, top, end, bottom.
                    .padding(10.dp, 15.dp, 30.dp, 5.dp)
                    .shadow(5.dp, RectangleShape,false)
            )
            Text(
                "hello world", Modifier.background(Purple40).width(60.dp).height(30.dp).clip(Shapes.large)
            )
            Box(
                modifier = Modifier
                    // 让 Box 的大小适应内容
                    .wrapContentSize()
                    .clip(Shapes.large)
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