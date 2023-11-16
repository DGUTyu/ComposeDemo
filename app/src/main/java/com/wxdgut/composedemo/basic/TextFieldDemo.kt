package com.wxdgut.composedemo.basic


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ShowTextFieldUse() {
    Column {
        // BasicTextField 允许用户通过硬件或软件键盘编辑文本，但没有提供提示或占位符等装饰
        var text by remember { mutableStateOf("") }
        BasicTextField(value = text, onValueChange = {
            text = it
        })

        // 默认样式为填充FilledTextField
        var text2 by remember { mutableStateOf("") }
        TextField(value = text2, onValueChange = {
            text2 = it
        })

        // OutlinedTextField 是轮廓样式版本
        var text3 by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text3,
            onValueChange = { text3 = it },
            label = { Text("Label") }
        )

        // 设置了 singleLine 再设置 maxLines 将无效
        Spacer(Modifier.padding(vertical = 4.dp))
        var text4 by remember { mutableStateOf("") }
        TextField(
            value = text4,
            onValueChange = { text4 = it },
            singleLine = true,
            label = {
                Text("邮箱")
            }
        )

        Spacer(Modifier.padding(vertical = 4.dp))
        var text5 by remember { mutableStateOf("") }
        TextField(
            value = text5,
            onValueChange = { text5 = it },
            leadingIcon = {
                // 接收来自一个 @Composable 函数的 lambda 表达式
                // 如 Icon(Icons.Filled.Search, null) 或 Text("联系人")。
                // 可以同时写上述两个表达式在 leadingIcon里 ，但不建议
                Text(
                    text = "联系人",
                    style = TextStyle(
                        baselineShift = BaselineShift.Superscript
                    )
                )
            }
        )

        Spacer(Modifier.padding(vertical = 4.dp))
        var text6 by remember { mutableStateOf("") }
        TextField(
            value = text6,
            onValueChange = { text6 = it },
            trailingIcon = {
                // 可以同时写上述两个表达式在 trailingIcon 里 ，但不建议
//                Text(
//                    text = "@163.com",
//                    style = TextStyle(
//                        baselineShift = BaselineShift.Superscript
//                    )
//                )
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    modifier = Modifier.graphicsLayer(
                        // 根据需要调整 translationY 的值，这里沿着Y轴向上平移4个像素的距离
                        translationY = with(LocalDensity.current) { (-4).dp.toPx() }
                    )
                )
            }
        )

        Spacer(Modifier.padding(vertical = 4.dp))
        var text7 by remember { mutableStateOf("") }
        TextField(
            value = text7,
            onValueChange = { text7 = it },
            label = { Text("Enter text") },
            modifier = Modifier.padding(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                //#FDFA94淡黄色 #00B42A绿色 #07828B 青色
                containerColor = Color(0xFFFDFA94),
                selectionColors = TextSelectionColors(
                    handleColor = Color(0xFF00B42A),
                    backgroundColor = Color(0xFF89E9E0)
                ),
                focusedIndicatorColor = Color.Transparent
            )
        )

        // 显示模式
        Spacer(Modifier.padding(vertical = 4.dp))
        var text8 by remember { mutableStateOf("") }
        var pwHidden by remember { mutableStateOf(false) }
        TextField(
            value = text8,
            onValueChange = { text8 = it },
            trailingIcon = {
                IconButton(
                    onClick = { pwHidden = !pwHidden }
                ) {
                    Icon(painterResource(id = R.drawable.img_eye), null, tint = Color.Unspecified)
                }
            },
            label = { Text("密码") },
            visualTransformation = if (pwHidden) PasswordVisualTransformation() else VisualTransformation.None
        )

        // 使用 BasicTextField 可以让你拥有更高的自定义效果
        Spacer(Modifier.padding(vertical = 4.dp))
        var text9 by remember { mutableStateOf("") }
        Box(
            // 设置 Box 的宽度充满父布局，高度为 80.dp，背景颜色为灰色
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            BasicTextField(
                value = text9,
                onValueChange = { text9 = it },
                // 设置输入框的样式，包括白色背景、圆角形状，高度为 35.dp，宽度充满父布局
                modifier = Modifier
                    .background(Color.White, CircleShape)
                    .height(35.dp)
                    .fillMaxWidth(),
                // decorationBox = { innerTextField -> ... }：lambda 表达式，用于自定义输入框的外观样式
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        IconButton(
                            onClick = { }
                        ) {
                            Icon(painterResource(id = R.drawable.img_mood), null)
                        }
                        Box(
                            //  用于包裹输入框，使其占据剩余的空间
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            innerTextField()
                        }
                        IconButton(
                            onClick = { },
                        ) {
                            Icon(Icons.Filled.Send, null)
                        }
                    }
                }
            )
        }
    }
}