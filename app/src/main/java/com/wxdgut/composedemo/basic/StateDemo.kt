package com.wxdgut.composedemo.basic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ShowStateUse() {
    Column(modifier = Modifier.padding(16.dp)) {
        // 方式1 mutableState:MutableState<String>
        // 推荐使用 val 来声明状态，因为它强调了不可变性，但仍然允许通过 value 属性来改变状态的值
        val mutableState = remember { mutableStateOf("") }
        if (mutableState.value.isNotEmpty()) {
            Text(text = "One, ${mutableState.value}!")
        }
        OutlinedTextField(
            value = mutableState.value,
            // 更新状态： 通过 mutableState.value 这种方式直接访问和更新状态的值
            onValueChange = { mutableState.value = it },
            label = { Text("Name1") }
        )
        // 方式2 name:String
        // 使用了 Kotlin 的属性委托语法，
        // 通过 by 关键字将 name 关联到了 remember { mutableStateOf("") } 所返回的 MutableState 对象
        // 可变的属性委托，它直接代表了 MutableState 的值
        var name by remember { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text(text = "Two, $name!")
        }
        OutlinedTextField(
            value = name,
            // 更新状态： 直接更新 name 变量的值就会触发状态更新
            onValueChange = { name = it },
            label = { Text("Name2") }
        )
        // 方式3 value:String, setValue:(String) -> Unit
        // 使用了 Kotlin 的解构语法
        // 将 value 和 setValue 绑定到了 remember { mutableStateOf("") } 所返回的 MutableState 对象
        val (value, setValue) = remember { mutableStateOf("") }
        if (value.isNotEmpty()) {
            Text(text = "Three, $value!")
        }
        OutlinedTextField(
            value = value,
            // 更新状态： 使用 setValue 函数来更新状态的值
            onValueChange = { setValue(it) },
            label = { Text("Name3") }
        )
    }
}

/**
 * remember经常与MutableState结合使用,在可组合项中声明MutableState对象的方法有三种
 */
@Composable
fun ThreeTypeUse() {
    // 使用 val mutableState = remember { mutableStateOf("") }
    val mutableState = remember { mutableStateOf("") }
    val oldValue1 = mutableState.value
    mutableState.value = "New Value 1"

    // 使用 var name by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    val oldValue2 = name
    name = "New Value 2"

    // 使用 val (value, setValue) = remember { mutableStateOf("") }
    val (value, setValue) = remember { mutableStateOf("") }
    val oldValue3 = value
    setValue("New Value 3")
}

/**
 * 在 Jetpack Compose 中，不应该直接使用可变对象（比如 ArrayList 或 mutableListOf()）作为状态数据。
 * 如果将可变对象直接用作状态数据，当它们发生变化时，Compose 并不会自动触发 UI 重新组合。
 * 建议使用不可变的数据结构，例如 listOf()，或者使用可观察的数据存储器，例如 State<List<T>>，来存储状态数据。
 * 这样做有助于确保状态变化能够被 Compose 察觉到，从而触发 UI 的重新组合。
 *
 * 下面是错误的用法：使用可变的 mutableListOf() 作为状态数据
 */
@Composable
fun IncorrectUsageWithState() {
    //mutableList:MutableList<String>
    val mutableList = remember { mutableListOf<String>() }

    // 修改 mutableList，但不会触发 UI 重新组合
    mutableList.add("Item")
}

/**
 * 正确的用法：使用 State<List<T>> 或不可变的 listOf() 作为状态数据
 *
 * remember 会将对象存储在组合中，当调用 remember 的可Composeable元素从组合中移除后，remember会自动被销毁。
 * 举例，如果你在一个屏幕上展示某个列表，当你离开这个屏幕（移除了可Composeable元素），
 * 与这个屏幕相关的 remember 数据将被销毁，以释放内存和资源。
 */
@Composable
fun CorrectUsageWithState() {
    //stateList:MutableState<List<String>>
    val stateList = remember { mutableStateOf(listOf<String>()) }

    // 修改 stateList 的值，触发 UI 重新组合
    stateList.value = stateList.value + listOf("Item")
}