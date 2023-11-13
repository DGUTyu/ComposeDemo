package com.wxdgut.composedemo.bean

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.R

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    // 创建一个能够检测卡片是否被展开的变量
    var isExpanded by remember { mutableStateOf(false) }
    // 使用 MaterialTheme 中的shape
    Surface(
        color = Color(android.graphics.Color.parseColor("#B7F4EC")),
        shape = MaterialTheme.shapes.medium,
        // tonalElevation标高越高，浅色主题的颜色越深，深色主题的颜色就越浅
        tonalElevation = 2.dp
    ) {
        Row(
            // 在我们的 Card 周围添加 padding、圆角、阴影
            modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(10.dp))
                //shadow 并不直接控制阴影的大小。elevation 参数指定了阴影的大小，但并不直接决定四条边的阴影大小是否一致
                .shadow(4.dp)
                .clickable { isExpanded = !isExpanded }
        )
        {
            Image(
                painterResource(id = R.drawable.img_compose_for_desktop),
                contentDescription = "Compose dev",
                //改变 Image 元素的大小,指定ContentScale后将图片裁剪成圆形,对齐方式垂直居中
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterVertically)
                    // 使用 MaterialTheme 中的颜色
                    .border(1.5.dp, MaterialTheme.colorScheme.error, CircleShape)
            )
            // 添加一个空的控件用来填充水平间距，设置 padding 为 8.dp
            Spacer(Modifier.padding(horizontal = 8.dp))
            Column {
                Text(
                    text = msg.author,
                    // 使用 MaterialTheme 中的style
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(Modifier.padding(vertical = 4.dp))
                Text(
                    text = msg.body,
                    // 使用 MaterialTheme 中的style
                    style = MaterialTheme.typography.bodySmall,
                    // 布局阶段确定最多显示一行，运行阶段，如果点击了就允许显示多行，再次点击则恢复显示一行
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    // Composable 大小的动画效果
                    modifier = Modifier.animateContentSize()
                )
            }
        }
    }
}

//Preview注解支持输入的属性及默认值有：name:string= "",group:Sting = "",apiLevel:Int = -1,widthDp:Int = -1,heightDp:Int = -1,
//locale:String = "",fontScale:Float = 1f,showSystemUi:Boolean = false,showBackground:Boolean = false,
//backgroundColor:Long = 0,uiMode:Int = 0,device:String = Devices.DEFAULT,wallpaper:Int = Wallpapers.NONE

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    // 用 Column 来垂直排列项目，用 Row 来水平排列项目，用 Box 来堆叠元素。
    val msg = Message("Hello", "Compose")
    val msg2 = Message("Android", "Studio")
    val msg3 = Message("666", "777")
    Column {
        MessageCard(msg)
        Text("*************")
        Text(msg.author)
        Text(msg.body)
        Row {
            MessageCard(msg = msg2)
            Text("-----")
            Text(text = msg2.author)
            Text(text = msg2.body)
        }
        Row {
            Box {
                MessageCard(msg = msg3)
            }
            Box {
                Text(text = "222")
                Text(text = "444")
            }
        }
    }
}