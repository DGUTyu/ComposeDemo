package com.wxdgut.composedemo.basic


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.outlined.Verified
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material.icons.sharp.Verified
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material.icons.twotone.Verified
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.wxdgut.composedemo.R
import com.wxdgut.composedemo.utils.ComposeUtils


@Preview(showBackground = true)
@Composable
fun ShowIconUse() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            //不管图标颜色如何，默认Icon色调为黑色,如需修改，请使用tint
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "矢量图",
                //不使用任何默认色调，则需要传入tint
                tint = Color.Red
            )

            Icon(
                imageVector = Icons.TwoTone.Delete,
                contentDescription = "矢量图",
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.svg_choice_press),
                contentDescription = "矢量图",
                tint = colorResource(id = R.color.cyan_2)
            )

            Icon(
                bitmap = ImageBitmap.imageResource(id = R.drawable.img_notice),
                contentDescription = "位图(图片)资源"
            )

            Icon(
                bitmap = ImageBitmap.imageResource(id = R.drawable.img_pass),
                contentDescription = "位图(图片)资源"
            )

            Icon(
                bitmap = ImageBitmap.imageResource(id = R.drawable.img_pass),
                contentDescription = "位图(图片)资源",
                //使用原始图标颜色
                tint = Color.Unspecified
            )

            Icon(
                painter = painterResource(id = R.drawable.img_notice),
                contentDescription = "画笔,可任意类型资源",
                tint = Color.Unspecified
            )

            Icon(
                painter = painterResource(id = androidx.core.R.drawable.notify_panel_notification_icon_bg),
                contentDescription = "画笔",
                tint = Color(android.graphics.Color.parseColor("#F77234"))
            )

            Icon(
                painter = painterResource(id = R.drawable.svg_choice_normal),
                contentDescription = "画笔",
            )
        }

        // Icons风格类型
        Row {
            Icon(Icons.Outlined.Verified, contentDescription = "勾勒轮廓", tint = Color.Red)
            Icon(Icons.Filled.Verified, contentDescription = "图形填充", tint = Color.Blue)
            Icon(Icons.Rounded.Verified, contentDescription = "端点均为圆角", tint = Color.Green)
            Icon(Icons.Sharp.Verified, contentDescription = "端点均为尖角", tint = Color.Red)
            Icon(Icons.TwoTone.Verified, contentDescription = "双色搭配", tint = Color.Black)
        }

    }
}