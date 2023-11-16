package com.wxdgut.composedemo.basic


import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.wxdgut.composedemo.utils.CenteredRow

/**
 * 创建 Data class 来记录不同状态下按钮的样式
 * 使用 MutableInteractionSource() 获取状态，根据不同状态创建样式
 */
data class ButtonState(var text: String, var textColor: Color, var buttonColor: Color)

@Preview(showBackground = true)
@Composable
fun ShowButtonUse() {
    Column {
        CenteredRow {
            Button(onClick = { /*TODO*/ }) {
                Text("确认")
            }

            Button(onClick = { /*TODO*/ }) {
                // 添加图标在文字的旁边
                Icon(
                    // Material 库中的图标，有 Filled, Outlined, Rounded, Sharp, TwoTone
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                // 添加间隔
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("喜欢")
            }

            // 获取按钮的状态
            val interactionState = remember { MutableInteractionSource() }
            /*
            val buttonState: ButtonState = when {
                interactionState.collectIsPressedAsState().value ->
                    ButtonState(
                        "Just Pressed",
                        Color.Red,
                        Color.Black
                    )

                else ->
                    ButtonState("Just Button", Color.White, Color.Red)
            }

            val text = buttonState.text
            val textColor = buttonState.textColor
            val buttonColor = buttonState.buttonColor
            */

            // 使用 Kotlin 的解构方法， 返回的 ButtonState 对象通过解构声明被分配给了三个变量
            // text、textColor 和 buttonColor 这三个变量然后在 Button 组件中使用。
            val (text, textColor, buttonColor) = when {
                interactionState.collectIsPressedAsState().value ->
                    ButtonState(
                        "Just Pressed",
                        Color.Red,
                        Color.Black
                    )

                else ->
                    ButtonState("Just Button", Color.White, Color.Red)
            }
            // 不同点击状态下的按钮状态
            Button(
                onClick = { /*TODO*/ },
                // 按钮状态传进去
                interactionSource = interactionState,
                elevation = null,
                // 设置为圆角矩形
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                ),
                // 按钮的宽度和高度，分别设置为最大和最小自然大小
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Min)
            ) {
                Text(text = text, color = textColor)
            }
        }
    }
}