package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.painterResource

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
    SubcomposeLayout(modifier= Modifier.background(Color(0xF5F6F7FF))) { constraints ->
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