package com.wxdgut.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wxdgut.composedemo.ui.theme.ComposeDemoTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowTextPreview()
        }
    }

    //Composable 函数一般用大写开头，为了和普通的函数作为区分
    @Preview(showBackground = true)
    @Composable
    fun ShowTextPreview() {
        ShowText("标题1", "标题2，会连在标题1后面")
    }

    @Composable
    fun ShowText(title: String, subTitle: String) {
        Text("${title}${subTitle}")
    }
}