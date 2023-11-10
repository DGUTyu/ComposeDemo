package com.wxdgut.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.bean.Message
import com.wxdgut.composedemo.ui.theme.Pink80
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
        //ShowModifierText()
        ScaffoldExample()
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

    //Scaffold、TopAppBar需要添加注解
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldExample() {
        var presses by remember { mutableStateOf(0) }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Top app bar") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Pink80,
                        titleContentColor = Color.White
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { presses++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            //默认显示在右下角
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text =
                    """
                    这是一个脚手架的例子。它使用Scaffold compositable的参数来创建一个屏幕，其中包含一个简单的顶部应用程序栏、底部应用程序栏和浮动操作按钮。
                    它还包含了一些基本的内部内容，例如本文。
                    您已按下浮动动作按钮 $presses 次。
                """.trimIndent(),
                )
                ShowImage()
            }
        }
    }

    @Composable
    fun ShowImage() {
        Row(
            Modifier.background(Pink80),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //定义图片Modifier
            val imageModifier = Modifier
                .size(90.dp, 120.dp)
                .border(BorderStroke(2.dp, Red))
                .background(Purple80)
                .clip( RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp))
            Column {
                Image(
                    painter = painterResource(id = R.drawable.img_android_horizontal),
                    contentDescription = "这是备注，可填null",
                    modifier = imageModifier
                )
            }
            Column(
                Modifier.padding(start = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_android_vertical),
                    contentDescription = null,
                    modifier = imageModifier
                )
            }
            Column(
                Modifier.padding(start = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.img_android_transparent_bg),
                    contentDescription = "原来的图片是正方形单一蓝色，改为单一绿色，拉伸图片，填充宽高",
                    modifier = imageModifier,
                    contentScale = ContentScale.FillBounds,
                    colorFilter = ColorFilter.tint(color = Color.Green, BlendMode.SrcAtop)
                )
            }
            Column(
                Modifier.padding(start = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.img_android_horizontal2),
                    contentDescription = "None:截取图片中心放大。缩放为Fit时，clip无效",
                    modifier = imageModifier,
                    contentScale = ContentScale.None
                )
            }
        }
    }
}