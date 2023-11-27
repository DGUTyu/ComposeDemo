package com.wxdgut.composedemo.basic

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wxdgut.composedemo.bean.Message
import java.util.Date

data class Fruit(val name: String, val price: Int)

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun ShowListUse() {
    //onlyColumn()
    //SimpleLazyColumn()
    //SimpleLazyColumn2()
    //StickyHeader()
    ClickDemo()
}

@Composable
private fun ClickDemo() {
    // List 数据源
    val fruits = listOf(
        Fruit("苹果", 12),
        Fruit("香蕉", 13),
        Fruit("橘子", 14),
        Fruit("葡萄", 15),
        Fruit("西瓜", 16)
    )
    // 高阶函数：接受一个函数作为参数，并在某个时机调用传递进来的函数
    // 它执行了传入的函数来显示 Toast 信息。在这个场景中，传入的函数实际上是一个在点击时处理 Toast 显示的操作。
    val toastFruitName = { name: String, context: Context ->
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
    }
    val context = LocalContext.current
    // 展示水果名列表
    LazyColumn {
        items(fruits) { fruit ->
            FruitsClick(fruit) {
                // 传递一个具体的函数充当 clickEvent 参数
                toastFruitName(fruit.name, context)
            }
        }
    }
}

//接受一个 Fruit 对象和一个回调函数作为参数
@Composable
private fun FruitsClick(item: Fruit, clickEvent: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = item.name,
            //clickEvent 在这里是一个回调函数，它不包含任何实际的逻辑，只是指定了当点击发生时应该执行的操作
            modifier = Modifier.clickable(onClick = clickEvent)
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun StickyHeader() {
    // 当显示分组数据的列表时，可用sticky header,但这是实验性 API ,在未来可能会变化或删除
    val sections = listOf("贡献者", "眠眠的粉丝")
    LazyColumn {
        sections.forEachIndexed { index, section ->
            stickyHeader {
                Text(
                    text = section,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF2F4FB))
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    fontWeight = FontWeight.W700,
                    color = Color(0xFF0079D3)
                )
            }
            when (index) {
                0 -> items(2) { i ->
                    Text(text = "这里是贡献者$i 的内容")
                }

                1 -> items(3) { i ->
                    Text(text = "这里是眠眠的粉丝$i 的内容")
                }
            }
        }
    }
}

@Composable
private fun SimpleLazyColumn2() {
    // 创建一些示例消息
    val messages = listOf(
        Message(author = "Alice", body = "Hello!"),
        Message(author = "Bob", body = "Hi there!"),
        Message(author = "Carol", body = "Welcome to the chat!")
    )
    // 在UI中显示消息列表
    MessageList(messages = messages)
}

@Composable
fun MessageList(messages: List<Message>) {
    Column {
        LazyColumn {
            items(messages) { message ->
                MessageItem(message = message)
            }
        }

        Box(Modifier.background(Color.Gray)) {
            LazyColumn(
                modifier = Modifier.border(5.dp, color = Color.Blue),
                // 在 Lazy组件中，设置 Lazy 组件里面内容的内边距时用 contentPadding
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                // 在项目之间添加4dp间距，用 Arrangement.spacedBy()
                verticalArrangement = Arrangement.spacedBy(4.dp),
                // 对 LazyRow 也可进行类似的操作
                // horizontalArrangement = Arrangement.spacedBy(4.dp)
                // 对LazyVerticalGrid，既接受垂直排列，也接受水平排列
            ) {
                items(messages) { message ->
                    MessageItem(message = message)
                }
            }
        }

        // items() 的扩展函数还有一个变体，叫做 itemsIndexed()，它提供了索引。
        LazyColumn {
            itemsIndexed(messages) { index, message ->
                MessageItem(index = index, message = message)
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    var timeStamp by remember { mutableStateOf(System.currentTimeMillis()) }
    Text(text = "${message.author}: ${message.body} :$timeStamp",
        modifier = Modifier.clickable {
            timeStamp = System.currentTimeMillis()
        })
}

@Composable
fun MessageItem(index: Int, message: Message) {
    Text(text = "[$index] ${message.author}: ${message.body}")
}

@Composable
private fun SimpleLazyColumn() {
    LazyColumn {
        // 添加单个项目
        item {
            Text(text = "First item")
        }
        // 添加五个项目
        items(5) { index ->
            Text(text = "Item: $index")
        }
        // 添加其他单个项目
        item {
            Text(text = "Last item")
        }
    }
}

@Composable
private fun OnlyColumn() {
    // 添加大量的文本项目以产生滚动
    var itemStates by remember { mutableStateOf(List(50) { "Item $it" }) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.LightGray
    ) {
        // 使用Column和verticalScroll Modifier创建可滚动的列
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // forEachIndexed函数允许你遍历列表中的每个元素，并提供索引和元素本身。
            itemStates.forEachIndexed { index, item ->
                // 使用remember保存每个项的状态
                val (currentText, setCurrentText) = remember { mutableStateOf(item) }
                // 创建点击更新文本的函数
                val updateTime = { updatedText: String ->
                    // 通过创建列表的可变副本，并在特定索引处更新为新的文本内容updatedText来更新状态
                    itemStates = itemStates.toMutableList().also {
                        it[index] = updatedText
                    }
                }
                Text(
                    text = currentText,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable {
                            val updatedItem = "Item $index - ${Date().time}"
                            setCurrentText(updatedItem)
                            updateTime(updatedItem)
                        }
                )
            }
        }
    }
}