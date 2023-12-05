package com.wxdgut.composedemo.basic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun RowAndColumnUse() {
    RowAndColumnArrangementUse()
    //RowAndColumnWeightUse()
}

@Composable
private fun RowAndColumnArrangementUse() {
    // Row 组件中的 horizontalArrangement 使用Arrangement属性
    // Arrangment : Center, Start, End, SpaceAround, SpaceBetween, SpaceEvenly
    // Column 组件中的 horizontalArrangement 使用Arrangement属性
    // Arrangment : Center, Bottom, Top, SpaceAround, SpaceBetween, SpaceEvenly
    Column {
        Row {
            RowArrangementUse()
        }
        Row(modifier = Modifier.weight(1f)) {
            ColumnArrangementUse()
        }
    }
}

@Composable
private fun RowAndColumnWeightUse() {
    Column {
        Row {
            Text("First item", Modifier.weight(1f))
            Text("Second item", Modifier.weight(2f))
            Text("Third item", Modifier.weight(3f))
        }
        Row(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier.fillMaxHeight()
            ) {
                Text("First item", Modifier.weight(1f))
                Text("Second item", Modifier.weight(2f))
                Text("Third item", Modifier.weight(3f))
            }
        }
    }
}

@Composable
private fun RowArrangementUse() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            // 将子项均匀地分布在主轴上，并在相邻子项之间创建一半的空间。
            // 同时，它还会在第一个子项前和最后一个子项后创建一个与其他间隔一半大小的空间。
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThreeBtn()
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            // 将子项均匀地分布在主轴上，但不会在第一个子项前和最后一个子项后创建额外的空间，也就是没有额外的空白间隔。
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThreeBtn()
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            // 将子项均匀地分布在主轴上，包括第一个子项前和最后一个子项后的额外空间，同时保持相邻子项之间的间隔相等。
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThreeBtn()
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            // Center
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThreeBtn()
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            // Center
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThreeBtn()
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            // Center
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThreeBtn()
        }
    }
}

@Composable
private fun ColumnArrangementUse() {
    Column(modifier = Modifier.fillMaxHeight()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ThreeBtn()
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ThreeBtn()
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ThreeBtn()
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                // Bottom  Center  Top
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ThreeBtn()
            }
        }
    }
}

@Composable
private fun ThreeBtn() {
    repeat(3) {
        Button(onClick = { /* Do something */ }) {
            Text(text = "Button ${it + 1}")
        }
    }
}
