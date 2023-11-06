package com.wxdgut.composedemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.diff.DeclarativeUIActivity
import com.wxdgut.composedemo.diff.ImperativeUIActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Text("命令式UI",
                    Modifier
                        .clickable {
                            toImperativeUIActivity()
                        }
                        .padding(top = 20.dp) // 设置上边距为20dp
                )

                Text("声明式UI",
                    Modifier
                        .clickable {
                            toDeclarativeUIActivity()
                        }
                        .padding(top = 20.dp) // 设置上边距为20dp
                )
            }
        }
    }

    //测试
    private fun test() {
        toSecondActivity()
    }

    //去SecondActivity
    private fun toSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    //命令式UI
    private fun toImperativeUIActivity() {
        val intent = Intent(this, ImperativeUIActivity::class.java)
        startActivity(intent)
    }

    //去声明式UI
    private fun toDeclarativeUIActivity() {
        val intent = Intent(this, DeclarativeUIActivity::class.java)
        startActivity(intent)
    }
}