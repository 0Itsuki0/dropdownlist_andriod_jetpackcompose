package com.example.dropdown_demo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import com.example.dropdown_demo.ui.theme.Dropdown_demoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Dropdown_demoTheme {

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    ContentView()
                }

            }
        }
    }
}

@Composable
fun DropdownList(itemList: List<String>, selectedIndex: Int, modifier: Modifier, onItemClick: (Int) -> Unit) {

    var showDropdown by rememberSaveable { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    // button
    Box(
        modifier = modifier
            .background(Color.Red)
            .clickable { showDropdown = true },
//            .clickable { showDropdown = !showDropdown },
        contentAlignment = Alignment.Center
    ) {
        Text(text = itemList[selectedIndex], modifier = Modifier.padding(3.dp))
    }

    // dropdown list
    Box() {
        if (showDropdown) {
            Popup(
                alignment = Alignment.TopCenter,
                properties = PopupProperties(
                    excludeFromSystemGesture = true,
                ),
                // to dismiss on click outside
                onDismissRequest = { showDropdown = false }
            ) {

                Column(
                    modifier = modifier
                        .heightIn(max = 90.dp)
                        .verticalScroll(state = scrollState)
                        .border(width = 1.dp, color = Color.Gray),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    itemList.onEachIndexed { index, item ->
                        if (index != 0) {
                            Divider(thickness = 1.dp, color = Color.LightGray)
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Green)
                                .fillMaxWidth()
                                .clickable {
                                    onItemClick(index)
                                    showDropdown = !showDropdown
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = item,)
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun ContentView() {
    val itemList = listOf<String>("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6")
//    val itemList = listOf<String>("Item 1", "Item 2", "Item 3")
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    var buttonModifier = Modifier.width(100.dp)

    Column(

        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DropdownList(itemList = itemList, selectedIndex = selectedIndex, modifier = buttonModifier, onItemClick = { selectedIndex = it})


        Text(text = "You have chosen ${itemList[selectedIndex]}",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .background(Color.LightGray),)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Dropdown_demoTheme {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ContentView()
        }
    }
}