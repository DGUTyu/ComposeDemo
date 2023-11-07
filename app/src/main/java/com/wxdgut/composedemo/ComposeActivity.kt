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
        setContent(content = {
            showTextPreview()
        })
    }

    @Preview(showBackground = true)
    @Composable
    fun showTextPreview() {
        showText(title = "标题1", subTitle = "标题2，会连在标题1后面")
    }

    @Composable
    fun showText(title: String, subTitle: String) {
        Text(text = "${title}${subTitle}")
    }
}