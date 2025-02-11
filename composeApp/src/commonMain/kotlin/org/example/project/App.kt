package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview



class RibbonTabInfo(val name: String) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): Any? = this@RibbonTabInfo
}

fun Modifier.ribbonTabInfo(name: String): Modifier {
    return then(RibbonTabInfo(name))
}



@OptIn(InternalResourceApi::class)
@Composable
fun RibbonTabRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit) {
    SubcomposeLayout(modifier = modifier.background(Color.Red)) {    constraints ->
        val menuItemPlaceables = subcompose("MenuTabItems", content = content)
            .map {
                it.measure(constraints)
            }

        var listOfTabNames = menuItemPlaceables.map {
            val parentData = it.parentData as RibbonTabInfo
            parentData.name
        }

        val moreItemsButtonPlaceable = subcompose("MoreItemsButton") {
            Text("^")
        }.map { it.measure(constraints) }
        val buttonMoreItemsMaxWidth = moreItemsButtonPlaceable.maxOf { placeable -> placeable.width}

        val maxWidth = constraints.maxWidth
        val layoutTotalHeight = menuItemPlaceables.maxOf { placeable -> placeable.height}
        var x =0
        var hiddenItemsCount = 0
        layout(constraints.maxWidth, layoutTotalHeight) {


            menuItemPlaceables.forEachIndexed { i, placeablesItem ->
                if ( x + placeablesItem.width < maxWidth - buttonMoreItemsMaxWidth) {
                    placeablesItem.place(x= x, y = 0)
                    x = x + placeablesItem.width
                }else
                {
                    hiddenItemsCount++
                }
            }
            if(hiddenItemsCount >0){



                val remainingItemsButtonPlaceable = subcompose("RemainingItemsButton") {
                    var expanded by remember { mutableStateOf(false) }

                    val options = listOf("Home", "Profile", "Settings")
                    var selectedOption by remember { mutableStateOf(options[0]) }
Box {

        Image(
            painterResource(
                org.jetbrains.compose.resources.DrawableResource(
                    "drawable:keyboard_arrow_down",
                    setOf(
                        org.jetbrains.compose.resources.ResourceItem(
                            setOf(),
                            "composeResources/ribboncompose.composeapp.generated.resources/drawable/keyboard_arrow_down_24px.xml",
                            -1,
                            -1
                        ),
                    )
                )
            ),
            "",
            modifier = modifier.clickable {  expanded = !expanded }
        )
//converter para um popup amanha e verificar o erro do
//if(expanded) {
  //      Popup(alignment = Alignment.Center, onDismissRequest = {expanded = false}) {
  //          Text("asd")

        //}
   // }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = modifier.padding(vertical = 0.dp)
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = {
                    Text(
                        option
                    )
                },
                onClick = {
                    selectedOption = option
                    expanded = false
                },
                modifier = modifier
            )
        }
    }

}
                // Text("^")
                }.map { it.measure(constraints) }
                remainingItemsButtonPlaceable.first().place(x=x, y= (layoutTotalHeight - remainingItemsButtonPlaceable.first().height))
            }
        }
    }


}

@Composable
fun SubcomposeExample() {
    SubcomposeLayout { constraints ->
        // First measure the content that defines the size
        val titlePlaceables = subcompose("Title") {
            Text("This is a title", style = MaterialTheme.typography.titleLarge)
        }.map { it.measure(constraints) }

        val titleHeight = titlePlaceables.maxOf { it.height }

        // Measure the content depending on the title size
        val bodyPlaceables = subcompose("Body") {
            Column {
                Text("Body text that depends on the title height")
                Text("Title height: $titleHeight px")
            }
        }.map { it.measure(constraints) }



        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place the title at the top
            var yOffset = 0
            titlePlaceables.forEach { placeable ->
                placeable.placeRelative(0, yOffset)
                yOffset += placeable.height
            }

            // Place the body below the title
            bodyPlaceables.forEach { placeable ->
                placeable.placeRelative(0, yOffset)
                yOffset += placeable.height
            }
        }
    }
}

@Composable
@Preview
fun RibbonTab(
    name : String,
    selected : Boolean,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    if(selected){
        Box(modifier = modifier.padding(horizontal = 16.dp).clickable { onClick() }.drawBehind {
            val strokeWidth = 3 * density
            val y = size.height - strokeWidth /2

            drawLine(
                Color.Black,
                Offset(0f,y),
                Offset(size.width, y),
                strokeWidth
            )
        }) {
            Text(name, fontWeight = FontWeight.Bold,modifier = modifier.padding(vertical = 4.dp))
        }
    }else{
        Box(modifier = modifier.padding(horizontal = 16.dp).clickable{ onClick()}) {
            Text(name, modifier = modifier.padding(vertical = 4.dp))
        }
    }
}

@Composable
@Preview
fun App() {
    var tabIndex by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(true) }
    MaterialTheme {
        Column {
        Row {
            RibbonTabRow(modifier = Modifier) { //we are going to start by creating our rows
                //Text("test",  modifier = Modifier.ribbonTabInfo("Home"))
                RibbonTab("Home", tabIndex == 0, onClick = { tabIndex = 0; visible = !visible}, modifier = Modifier.ribbonTabInfo("Home"))
                RibbonTab("Insert", tabIndex == 1, onClick = { tabIndex = 1; visible = !visible}, modifier = Modifier.ribbonTabInfo("Insert"))
                RibbonTab("Draw", tabIndex == 2, onClick = { tabIndex = 2; visible = !visible}, modifier = Modifier.ribbonTabInfo("Draw"))
                RibbonTab("Design", tabIndex == 3, onClick = { tabIndex = 3; visible = !visible}, modifier = Modifier.ribbonTabInfo("Design"))
            }
        }
        Row{
            when(tabIndex) {
                0 -> { Text("This is home!")}
                1 -> { Text("This is insert!")}
                2 -> { Text("This is draw!")}
                3 -> { Text("This is design!")}
            }
        }
            }
    }
}