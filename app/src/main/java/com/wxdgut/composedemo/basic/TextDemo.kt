package com.wxdgut.composedemo.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wxdgut.composedemo.R
import com.wxdgut.composedemo.utils.ComposeUtils

@Preview(showBackground = true)
@Composable
fun ShowTextUse() {
    //默认情况下 Text 并不能进行复制等操作，需要设置 SelectionContainer 来包装 Text
    SelectionContainer {
        Column {
            //一般情况下，Text 不会水平居中。想要实现居中的效果，可在Text外围写一个Box,Row,Column 等
            Box(
                modifier = Modifier.fillMaxWidth(),
                // 水平靠左： Alignment.Start ; 水平靠右： Alignment.End
                // 若Column有Modifier.fillMaxWidth()的属性或者指定了宽度/大小，
                // 则可直接在Text里面写 Modifier.align(Alignment.CenterHorizontally) 设置水平居中
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.hello_android),
                    // 斜体
                    style = MaterialTheme.typography.headlineMedium.copy(fontStyle = FontStyle.Italic),
                    // 文字增加删除线
                    textDecoration = TextDecoration.LineThrough,
                    // 加粗显示
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = stringResource(id = R.string.hello_world).repeat(2),
                style = TextStyle(
                    // 设置字体的粗细。数字的范围通常在 100 到 900 之间，表示从 Thin 到 Black 的不同权重。
                    fontWeight = FontWeight.W900,
                    // 表示使用 20sp（缩放独立像素）的字体大小。它会随着用户设备上的字体大小设置而进行缩放
                    fontSize = 20.sp,
                    // 设置字体的字距（字符间距）
                    letterSpacing = 7.sp,
                    // 斜体
                    fontStyle = FontStyle.Italic
                ),
                // 文字增加下划线
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = "Hello World Title1,Hello World Title2,Hello World Title3,Hello World Title4,Hello World Title5",
                style = MaterialTheme.typography.headlineMedium,
                // maxLines 将文本限制在指定的行数之间，如果文本足够短则不会生效，若超过 maxLines 所规定的行数则会截断
                maxLines = 1,
            )
            Text(
                text = "Hello World Title1,Hello World Title2,Hello World Title3,Hello World Title4,Hello World Title5",
                style = MaterialTheme.typography.headlineMedium,
                // maxLines 将文本限制在指定的行数之间，如果文本足够短则不会生效，若超过 maxLines 所规定的行数则会截断
                maxLines = 1,
                // overflow 参数可以帮助我们处理溢出的视觉效果,即 ...
                overflow = TextOverflow.Ellipsis,
            )
            // 在垂直方向上添加了一个垂直方向上的 padding，高度为 15.dp。
            // Spacer 元素可以用来在布局中创建空白区域，调整元素之间的间距。
            Spacer(Modifier.padding(vertical = 15.dp))
            Text(
                // 显示重复 2 次
                text = "My Font ".repeat(2),
                modifier = Modifier
                    .background(ComposeUtils.getColor(colorId = R.color.cyan_2))
                    .fillMaxWidth(),
                // 当我们在 Text 中设置了 fillMaxWidth() 之后，我们可以指定 Text 的对齐方式
                textAlign = TextAlign.Center,
                // 使用 lineHeight 参数可以让我们指定 Text 中每行的行高间距，一般建议使用 sp（缩放独立像素）
                lineHeight = 30.sp,
                // 使用 fontFamily 参数自定义字体，如加载 res/font 下的字体。注意资源名称必须是小写字母、数字或下划线，不能包含大写字母
                //fontFamily = FontFamily.Cursive
                fontFamily = FontFamily(Font(R.font.roboto_black, FontWeight.Medium))
            )
            // buildAnnotatedString 让一个 Text 语句中使用不同的样式，比如粗体提醒，特殊颜色
            var timeStamp by remember { mutableStateOf(System.currentTimeMillis()) }
            val specialText = buildAnnotatedString {
                append("Normal ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Bold ")
                }
                withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                    append("Strike Through ")
                }
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append("$timeStamp ")
                }
                append("More")
            }
            Text(
                text = specialText,
                // clickable 有自带的波纹效果
                modifier = Modifier
                    .clickable(
                        onClick = { timeStamp = System.currentTimeMillis() },
                        // 取消波纹效果需要添加两个参数
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    )
                    // 添加一些 padding，提升点击区域
                    .padding(16.dp),
                color = Color.Blue
            )

            // ClickableText:检测 Text 中的部分点击
            // 使用pushStringAnnotation、getStringAnnotations、pop来实现点击区域的判断并不准确，这里不做介绍
            var presses by remember { mutableStateOf(0) }
            val clickText = buildAnnotatedString {
                append("勾选即代表同意")
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0E9FF2), fontWeight = FontWeight.W900,
                        fontSize = 30.sp,
                        // BaselineShift,文字互相对齐的基准线,有3个默认的选项.Superscript:0.5 Subscript:-0.5 None:0.0
                        baselineShift = BaselineShift.Subscript
                    )
                ) {
                    append("用户协议")
                }
                append("，你按到了第$presses 位的文字")
            }
            ClickableText(
                text = clickText,
                // offset 参数表示点击位置的偏移量，而不是点击的字符位置。但两者的值比较接近
                onClick = { offset ->
                    presses = offset
                }
            )

            Text("Uses MaterialTheme's provided alpha")
            // CompositionLocalProvider，此改变只针对该作用域内的组件。常用于Text设置不透明度来传达不同的重要程度
            CompositionLocalProvider(
                //提供了 MaterialTheme.colorScheme.onSurface 的颜色，然后通过 copy 函数调整了 alpha 值为 0.5f
                //LocalContentColor provides Color.Red.copy(alpha = 0.5f)
                LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            ) {
                Text("Medium value provided for LocalContentAlpha")
                Text("This Text also uses the medium value")
                CompositionLocalProvider(
                    LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f)
                ) {
                    Text("This Text uses the disabled alpha now")
                }
            }

        }
    }
}