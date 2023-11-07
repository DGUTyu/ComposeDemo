package com.wxdgut.composedemo.bean

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Text(text = msg.author)
    Text(msg.body)
}

//Preview注解支持输入的属性及默认值有：name:string= "",group:Sting = "",apiLevel:Int = -1,widthDp:Int = -1,heightDp:Int = -1,
//locale:String = "",fontScale:Float = 1f,showSystemUi:Boolean = false,showBackground:Boolean = false,
//backgroundColor:Long = 0,uiMode:Int = 0,device:String = Devices.DEFAULT,wallpaper:Int = Wallpapers.NONE

//这段代码在 屏幕上创建了两个 Text 元素。然而，由于我们并没有指定如何排列它们，这两个 Text 元素重叠在了一块。
@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    //MessageCard(msg = Message(author = "Hello Compose", "I am LuYao"))
    MessageCard(Message("Hello Compose2", "I am LuYao"))
}