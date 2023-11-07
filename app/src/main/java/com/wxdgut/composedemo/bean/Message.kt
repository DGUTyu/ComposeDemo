package com.wxdgut.composedemo.bean

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Text(msg.author)
    Text(msg.body)
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