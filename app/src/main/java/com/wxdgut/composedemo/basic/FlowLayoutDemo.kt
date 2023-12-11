package com.wxdgut.composedemo.basic


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.utils.SplitScreenContentHorizontal


@Preview(showBackground = true)
@Composable
fun FlowLayoutUse() {
    val filters = listOf(
        "1Android Studio", "2Jetpack", "3Compose", "4Hello", "5World", "6You are Welcome"
    )

    // 这种写法不会有效果
    // 当你调用 flowRowUseContent(filters) 时，实际上你获取的是一个 @Composable 函数类型。
    //flowRowUseContent(filters)

    // 这种写法才会有效果
    // 要在 Composable 内使用它，你需要在它后面加上 ()，以便将其作为函数调用执行， 这样才能将其内容放置在 Composable 内
    //flowColumnUseContent(filters)()

    //flowRowUseContent(filters)()，flowColumnUseContent(filters)()同时预览，预览界面是重叠的，实际运行并不会重叠显示

    // 使用示例：flowRowUseContent并不会占满宽度，但flowColumnUseContent也显示不全。预览与实际也不同
    //Row {
    //flowRowUseContent(filters)()
    //flowColumnUseContent(filters)()
    //}

    Row(modifier = Modifier.height(200.dp)) {
        SplitScreenContentHorizontal(
            // 均等划分布局
            contents = listOf(
                // 这些流式布局还允许使用权重进行动态调整大小，以将项目分配到容器中
                flowRowUseContent2(filters),
                flowColumnUseContent2(filters)
            )
        )
    }
}

// 使用了 typealias 来定义了 ComposableContent 类型，它是一个函数类型，可以接受 @Composable 函数
typealias ComposableContent = @Composable () -> Unit

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
fun flowRowUseContent(filters: List<String>): ComposableContent = {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEach { title ->
            var selected by remember { mutableStateOf(false) }
            val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }
            FilterChip(
                selected,
                onClick = { selected = !selected },
                label = { Text(title) },
                leadingIcon = if (selected) leadingIcon else null
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
fun flowColumnUseContent(filters: List<String>): ComposableContent = {
    FlowColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEach { title ->
            var selected by remember { mutableStateOf(false) }
            val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }
            FilterChip(
                selected,
                onClick = { selected = !selected },
                label = { Text(title) },
                leadingIcon = if (selected) leadingIcon else null
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
fun flowRowUseContent2(filters: List<String>): ComposableContent =
    createFlowContent(isRow = true, filters = filters)

@OptIn(ExperimentalMaterial3Api::class)
fun flowColumnUseContent2(filters: List<String>): ComposableContent =
    createFlowContent(isRow = false, filters = filters)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
fun createFlowContent(
    isRow: Boolean,
    filters: List<String>
): ComposableContent = {
    if (isRow) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FlowContent(filters)
        }
    } else {
        FlowColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FlowContent(filters)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FlowContent(filters: List<String>) {
    filters.forEach { title ->
        var selected by remember { mutableStateOf(false) }
        val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }
        FilterChip(
            selected,
            onClick = { selected = !selected },
            label = { Text(title) },
            leadingIcon = if (selected) leadingIcon else null
        )
    }
}