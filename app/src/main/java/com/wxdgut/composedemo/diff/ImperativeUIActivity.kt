package com.wxdgut.composedemo.diff

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

/**
 * 在命令式UI中，手动构建一个完整的 UI 实例，并在随后的变化时手动更新它
 */
class ImperativeUIActivity : Activity() {
    private var count = 0 // 计数器
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 创建一个线性布局
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        // 创建一个 TextView
        textView = TextView(this)
        textView.text = "初始文本"

        // 将 TextView 添加到线性布局中
        linearLayout.addView(textView)

        // 设置线性布局作为当前 Activity 的内容视图
        setContentView(linearLayout)

        // 在随后的操作中，您可以调用 setText 方法来更新 TextView 的文本
        updateUI()

        textView.setOnClickListener {
            count++
            updateCounterText()
        }
    }

    private fun updateUI() {
        // 在需要更新 UI 的地方调用这个方法
        textView.text = "更新后的文本"
    }

    private fun updateCounterText() {
        // 更新文本视图显示计数值
        textView.text = "Count: $count"
    }

}