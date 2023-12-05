package com.wxdgut.composedemo.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ModifierUse() {
    ModifierOrder()
}

@Composable
private fun ModifierOrder() {
    // 由于每个函数都会对上一个函数返回的 Modifier 进行更改，因此顺序会影响UI的展示效果
    Row {
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(90.dp)
                .background(Color.Blue, shape = RoundedCornerShape(50))
                // 由于background在padding前，所以Modifier会先给Box设置背景，然后再设置边距
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "RoundButton", color = Color.White)
        }

        Box(
            modifier = Modifier
                .width(150.dp)
                .height(90.dp)
                .padding(20.dp)
                .background(Color(0xFF3ADF00), shape = RoundedCornerShape(50)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "RoundButton", color = Color.White)
        }
    }
}