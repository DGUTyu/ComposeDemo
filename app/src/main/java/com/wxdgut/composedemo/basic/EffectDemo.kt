package com.wxdgut.composedemo.basic

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ShowEffectUse() {
    //IntroduceIssue()
    //SideEffectExample()

    // 当 LaunchedEffectUse 组件进入屏幕时，协程开始运行，当组件不再可见时，协程会被取消。
    //LaunchedEffectUse()
    // 当你希望 LaunchedEffect 在特定情况下触发时，即使参数一样，你也需要适当地使用控制流语句来控制效果的触发
    //ConditionalLaunchedEffect(condition = false)

    //RememberCoroutineScopeUse(true)

    //SideEffectUse()

    DisposableEffectUse()
}

@Composable
fun DisposableEffectUse2(dispatcher: OnBackPressedDispatcher) {
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // 处理返回按钮按下事件
                Log.e("DisposableEffectUse2:", "handleOnBackPressed")
            }
        }
    }
    DisposableEffect(dispatcher) {
        Log.e("DisposableEffectUse2", "Composable entered addCallback before")
        dispatcher.addCallback(backCallback)
        Log.e("DisposableEffectUse2", "Composable entered addCallback after")
        onDispose {
            backCallback.remove() // avoid leaks!
            Log.e("DisposableEffectUse2", "Composable disposed")
        }
    }
}

@Composable
fun DisposableEffectUse() {
    var data by remember { mutableStateOf("Initial Data") }
    DisposableEffect(Unit) {
        // 在进入组合时执行
        Log.d("DisposableEffectUse", "Composable entered")
        onDispose {
            // 在组合不再处于活动状态时执行
            Log.d("DisposableEffectUse", "Composable disposed")
            // 在这里进行清理资源的操作，例如取消任务、关闭流等
        }
    }
    Column {
        Text("Data: $data")
        val coroutineScope = rememberCoroutineScope()
        Button(onClick = { data = "Updated Data" }
        ) {
            Text("Update Data")
        }
    }
}

@Composable
fun SideEffectUse() {
    var count by remember { mutableStateOf(0) }
    var context = LocalContext.current
    SideEffect {
        Log.d("SideEffectUse", "Composable recomposed with count: $count")
    }
    Column {
        Text("Count: $count")
        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}

@Composable
fun RememberCoroutineScopeUse(condition: Boolean) {
    Column {
        var elapsedTime by remember { mutableStateOf(0) }
        val coroutineScope = rememberCoroutineScope()
        LaunchedEffect(condition) {
            if (condition) {
                coroutineScope.launch {
                    while (true) {
                        delay(1000) // 每秒更新一次
                        elapsedTime++
                    }
                }
            }
        }
        Text(text = "rememberCoroutineScope Elapsed time: $elapsedTime seconds")

        // 模拟登录按钮
        val scope = rememberCoroutineScope()
        var jsonResponse by remember { mutableStateOf("") }
        Column {
            Button(
                onClick = {
                    scope.launch {
                        // 模拟网络请求延迟 3 秒钟
                        delay(3000)
                        // 模拟获取的 JSON 字符串
                        val jsonResult = "{ \"data\": \"someData\" }"
                        jsonResponse = jsonResult
                    }
                }
            ) {
                Text("Fetch Data")
            }
            // 显示获取的 JSON 字符串
            Text(text = "Response: $jsonResponse")
        }
    }
}

@Composable
private fun LaunchedEffectUse() {
    var elapsedTime by remember { mutableStateOf(0) }
    // 第一个参数表示 key，用于标识 LaunchedEffect。这个 key 用于检测何时启动和取消 LaunchedEffect。
    // 通常，该 key 应该是一个不变的值，以确保在组件重新组合时可以正确地管理和取消之前的效果。
    // 给一个固定的布尔值，在 Composable 组件重新组合时，这个 key 并不会发生变化。
    // 在不同的组件实例之间，这个 key 是相同的
    LaunchedEffect(true) {
        while (true) {
            delay(1000) // 每秒更新一次
            elapsedTime++
        }
    }
    Text(text = "LaunchedEffect Elapsed time: $elapsedTime seconds")
}

/**
 * 假设你希望在某个条件满足时触发 LaunchedEffect，而不是在组件创建时立即触发时，
 * 需要适当地使用控制流语句，例如 if 语句，来控制效果的触发
 */
@Composable
fun ConditionalLaunchedEffect(condition: Boolean) {
    var elapsedTime by remember { mutableStateOf(0) }
    if (condition) {
        LaunchedEffect(true) {
            while (true) {
                delay(1000) // 每秒更新一次
                elapsedTime++
            }
        }
    }
    Text(text = "Conditional LaunchedEffect Elapsed time: $elapsedTime seconds")
}


/**
 * Compose 中的附带效应指的是在组合函数内部执行的对外部环境的影响，
 * 如对变量的更改、对外部函数的调用或对状态的更新。
 * 在 Compose 中，这些附带效应可能会触发 UI 的重新组合，或者导致状态的变化。
 *
 * 附带效应的例子
 */
@Composable
fun SideEffectExample() {
    var counter by remember { mutableStateOf(0) }
    Column {
        // 附带效应：当点击按钮时，会增加 counter 的值
        Button(onClick = { counter++ }) {
            Text("Increase Counter")
        }
        // 附带效应：当 counter 改变时，会重新组合并更新 UI
        Text("Counter value: $counter")
    }
}

@Composable
private fun IntroduceIssue() {
    val count = remember { mutableStateOf(0) }
    Box {
        Text(text = "count:${count.value}", modifier = Modifier.clickable {
            count.value++
        })
    }
    // 如果想在这里实现延迟5秒之后，count的值自动累加1，怎么办呢？
    // 如果想在这里实现网络请求，成功后，再刷新页面，怎么办呢？
    // 这种场景称之为发生在组件树作用于之外的状态变化
}