package com.test.list_view_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.test.list_view_compose.ui.theme.ListviewcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListviewcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListViewExample()
                }
            }
        }
    }
}

@Composable
fun ListViewExample() {
    val items = createDummyList()
    LazyColumn {
        itemsIndexed(items) { index, item ->
            when (item) {
                is ListItem.Header -> HeaderItem(item)
                is ListItem.Item -> RegularItem(item)
            }
        }
    }
}

@Composable
fun HeaderItem(item: ListItem.Header) {
    // UI for header item
    Text(
        text = item.title,
        style = TextStyle(fontWeight = FontWeight.Bold),
        modifier = Modifier
            .background(Color.LightGray)
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth()
    )
}

@Composable
fun RegularItem(item: ListItem.Item) {
    // UI for regular item
    Text(
        text = item.text,
        modifier = Modifier
            .background(Color.White)
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .fillMaxWidth()
    )
}

fun createDummyList(): MutableList<ListItem> {
    val items = mutableListOf<ListItem>()
    for (i in 0 until 100) {
        if (i % 5 == 0) {
            val headerItem = ListItem.Header("Header $i")
            items.add(headerItem)
        } else {
            val item = ListItem.Item("Item $i")
            items.add(item)
        }
    }
    return items
}

sealed class ListItem {
    data class Header(val title: String) : ListItem()
    data class Item(val text: String) : ListItem()
}