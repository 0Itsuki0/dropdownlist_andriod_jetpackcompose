import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex

@Composable
fun backup() {
    val dropdownList = listOf<String>("Item 1", "Item 2", "Item 3", )
//    val showDropdown by remember { mutableStateOf(false) }
//    val selectedIndex by remember { mutableStateOf(false) }
//    val buttonTitle = remember {
//        mutableStateOf("Show Pop Up")
//    }
    var showDropdown by rememberSaveable { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }


    // on the below line we are creating a column
    Column(

        // in this column we are specifying
        // modifier to add padding and fill
        // max size
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),

        // on below line we are adding horizontal alignment
        // and vertical arrangement
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // on the below line we are creating a button
        Box(
            modifier = Modifier
                .background(Color.Red)
                .clickable {
                    showDropdown = !showDropdown
                }
        ) {

            // on the below line we are creating a text for our button.
            Text(text = dropdownList[selectedIndex], modifier = Modifier.padding(3.dp))
        }

        // on below line we are creating a box to display box.
        Box(Modifier.zIndex(5.0F)) {
            // on below line we are specifying height and width
            val popupWidth = 300.dp
            val popupHeight = 100.dp

            // on below line we are checking if dialog is open
            if (showDropdown) {
                // on below line we are adding pop up
                Popup(
                    // on below line we are adding
                    // alignment and properties.
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties()
                ) {

                    // on the below line we are creating a box.
                    Box(
                        // adding modifier to it.
                        Modifier
                            .size(popupWidth, popupHeight)
                            .padding(top = 5.dp)
                            // on below line we are adding background color
                            .background(Color.Green)
                            // on below line we are adding border.
                            .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp))
                    ) {

                        // on below line we are adding column
                        Column(
                            // on below line we are adding modifier to it.
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp),
                            // on below line we are adding horizontal and vertical
                            // arrangement to it.
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // on below line we are adding text for our pop up
                            Text(
                                // on below line we are specifying text
                                text = "Welcome to Geeks for Geeks",
                                // on below line we are specifying color.
                                color = Color.White,
                                // on below line we are adding padding to it
                                modifier = Modifier.padding(vertical = 5.dp),
                                // on below line we are adding font size.
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
        Text(text = "some other text", modifier = Modifier.padding(3.dp))

    }
}