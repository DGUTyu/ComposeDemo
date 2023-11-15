package com.wxdgut.composedemo.basic

import android.content.Context
import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.wxdgut.composedemo.R
import com.wxdgut.composedemo.ui.theme.Purple80

@Preview(showBackground = true)
@Composable
fun ShowImageUse() {
    Column {
        // 在Jetpack Compose中，有三种主要的方式来使用Image，每一种方式都适用于不同类型的图像
        // 方式1 使用 ImageVector：Image 组件可用于显示矢量图像。
        // 参数 imageVector 接受 ImageVector 类型的图像。
        // 这是一种适用于矢量图像的方式。

        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Account Circle"
        )

        // 方式2 使用 Painter：Image 组件也可以通过传递 Painter 对象来显示图像。
        // 这种方式适用于自定义图像绘制逻辑，可以使用 Painter 的子类或自定义实现。
        // 需要注意的是：
        // 某些 Painter 的实现可能不提供固有的尺寸（intrinsic size）。
        // 在 Compose 中，intrinsic size 是一个组合中元素的推荐尺寸，通常由组合的内容决定。
        // 当使用 Painter 时，如果该 Painter 实现没有提供固有尺寸（intrinsic size），
        // 那么在没有显式指定 LayoutModifier 的情况下，Image 可能会尺寸为零，因此不会绘制任何内容。
        // 这种情况通常出现在一些 Painter 实现，比如 ColorPainter，它的目标是填充整个边界，而不关心具体的固有尺寸。
        // 为了解决这个问题，你可以在 Image 的 Modifier 中添加一个 size，以确保给定的 Painter 能够有足够的空间来显示内容。例如：
//        Image(
//            painter = ColorPainter(color = Color.Red),
//            contentDescription = "Red Square",
//            //为 ColorPainter 提供了足够的空间（宽度和高度均为 100.dp）来显示内容。不添加size，则会尽可能填充整个边界
//            modifier = Modifier.size(100.dp)
//        )

        // 创建一个自定义的 Painter
        val customPainter = remember {
            object : Painter() {
                // 值得注意的是，intrinsicSize 属性是 Painter 的一个重要属性，用于指定图像的固有大小。
                // 这个值会影响到 Image 组件的布局。
                // 在这个例子中，我们将 intrinsicSize 设置为一个大小为 (100.0f, 100.0f) 的矩形。
                override val intrinsicSize: Size
                    get() = Size(100.0f, 100.0f)

                override fun DrawScope.onDraw() {
                    // 在这里实现自定义的绘制逻辑，如使用 drawRect 绘制了一个青色矩形
                    drawRect(color = Color.Cyan)
                }
            }
        }
        // 使用 Image 组件显示自定义的 Painter
        Image(
            painter = customPainter,
            contentDescription = "Localized description",
            modifier = Modifier.size(100.dp, 100.dp)
        )

        // 方式3 使用 ImageBitmap：Image 组件还可以用于显示位图图像。
        // 使用 ImageBitmap 类型的图像，可以通过直接传递 ImageBitmap 或使用 BitmapPainter 进行显示。
        // 这适用于需要处理位图图像的情况。

        // 通过某种方式创建 ImageBitmap
        val imageBitmap = ImageBitmap.imageResource(id = R.drawable.img_android_transparent_bg)
        Image(
            bitmap = imageBitmap,
            contentDescription = "Localized description"
        )

        Row {
            Surface(
                // 可以使用 Surface 来设置形状，或者在 Image 组件中使用 modifier.clip() 来裁剪形状
                shape = CircleShape,
                // 边框方式1
                //border = BorderStroke(5.dp, Color.Red)
            ) {
                //常规用法
                Image(
                    painter = painterResource(id = R.drawable.img_android_horizontal2),
                    contentDescription = "这是描述，可填null",
                    // modifier = Modifier.size(350.dp)
                    // 因为Image的contentScale默认是ContentScale.Fit，所以只有左右两边变成了圆形，而上下并没有
                    // 想要裁剪圆形，需要使用ContentScale.Crop，同时设置size。
                    // 注意：如果外部可用的size不够当前设置的大小时，圆形不会起效果。可以试试350dp与150dp
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        // 边框方式2
                        .border(
                            BorderStroke(5.dp, Color.Blue),
                            // 指定边框形状
                            RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)
                        )
                )
            }

            //加载网络图片
            val url = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png"
            val rainbowColorsBrush = remember {
                Brush.sweepGradient(
                    listOf(
                        Color(0xFF9575CD),
                        Color(0xFFBA68C8),
                        Color(0xFFE57373),
                        Color(0xFFFFB74D),
                        Color(0xFFFFF176),
                        Color(0xFFAED581),
                        Color(0xFF4DD0E1),
                        Color(0xFF9575CD)
                    )
                )
            }
            val imageModifier = Modifier
                .size(120.dp)
                .border(
                    //为图片加上彩色边框
                    BorderStroke(4.dp, rainbowColorsBrush),
                    RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)
                )
                .background(Purple80)
                .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp))
                //图片转换为自定义宽高比
                .aspectRatio(30f / 10f)
            //加载图片方式1：
            //AsyncImage(
            //model = url,
            //contentDescription = null,
            //modifier = imageModifier
            //)
            //加载图片方式2：
            val modelBuilder = ImageRequest.Builder(LocalContext.current)
                .data(url ?: "")
                //是否在图片加载时启用淡入淡出效果
                .crossfade(false)
                //是否允许使用硬件加速
                .allowHardware(true)
                .build()
            Image(
                painter = rememberAsyncImagePainter(model = modelBuilder),
                contentDescription = "下载网络图片,只使用其中一个加载方式即可",
                modifier = imageModifier
            )

            // 加载Gif图像
            val gif_url =
                "https://s1.chu0.com/src/img/gif/db/dba873378578488a9de51f01c101cd6a.gif?e=1735488000&token=1srnZGLKZ0Aqlz6dk7yF4SkiYf4eP-YrEOdM1sob:ENutG45YK-AdEpn2dSKWQRZ_8qY="
            // 在 @Composable 函数中使用 LocalContext.current 获取上下文
            val context: Context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(coil.decode.GifDecoder.Factory())
                    }
                }
                .build()
            Image(
                painter = rememberAsyncImagePainter(
                    gif_url,
                    imageLoader = imageLoader
                ),
                contentDescription = null,
                modifier = imageModifier
            )
        }

        // 加载 Svg 图像
        val svgImgLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
        var flag by remember { mutableStateOf(false) }
        // 使用 animateDpAsState 创建一个动画状态 size，该状态会在 flag 改变时从当前值过渡到目标值。
        // 这里目标值是根据 flag 的状态决定的，如果 flag 为 true，目标值是 450.dp，否则是 50.dp
        val size by animateDpAsState(targetValue = if (flag) 450.dp else 50.dp, label = "")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    // 方式1
                    // data = "https://coil-kt.github.io/coil/images/coil_logo_black.svg",
                    // 方式2
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = "https://coil-kt.github.io/coil/images/coil_logo_black.svg")
                        .apply<ImageRequest.Builder>(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.img_android_transparent_bg)//占位图
                            crossfade(true)//淡出效果
                        }).build(),
                    imageLoader = svgImgLoader
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(size)
                    .clickable(
                        onClick = { flag = !flag },
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    )
            )
        }

    }

}