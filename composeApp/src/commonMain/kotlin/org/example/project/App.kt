package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.painterResource

import org.jetbrains.compose.ui.tooling.preview.Preview

import ribboncompose.composeapp.generated.resources.Res
import ribboncompose.composeapp.generated.resources.noteL
import ribboncompose.composeapp.generated.resources.noteS
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


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
                RibbonTab("File", tabIndex == 0, onClick = { tabIndex = 0; visible = !visible}, modifier = Modifier.ribbonTabInfo("File"))
                RibbonTab("Insert", tabIndex == 1, onClick = { tabIndex = 1; visible = !visible}, modifier = Modifier.ribbonTabInfo("Insert"))
                RibbonTab("Draw", tabIndex == 2, onClick = { tabIndex = 2; visible = !visible}, modifier = Modifier.ribbonTabInfo("Draw"))
                RibbonTab("Design", tabIndex == 3, onClick = { tabIndex = 3; visible = !visible}, modifier = Modifier.ribbonTabInfo("Design"))
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                val lineColor = RibbonConfiguration.colorPattern.dividerColor // Example hex color (#FF5733)
                val strokeWidth = 2.dp.toPx()
                drawLine(
                    color = lineColor,
                    start = androidx.compose.ui.geometry.Offset(0f, size.height),
                    end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
            }){
            when(tabIndex) {
                0 -> {
                    RibbonTabBody(
                        RibbonTabComponents(
                        listOf<RibbonGroup>(
                            RibbonGroup(
                                name = "group1",
                                sizeDefinition = RibbonGroup.SizeDefinition.OneButton,
                                idealSize = RibbonGroup.RibbonComponentSize.Large,
                                ribbonSubComponents = listOf<IRibbonSubComponent>(
                                    RibbonButton(
                                        name="Open",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    )
                                )
                            ),
                            RibbonGroup(
                                name = "group2",
                                sizeDefinition = RibbonGroup.SizeDefinition.TwoButtons,
                                idealSize = RibbonGroup.RibbonComponentSize.Large,
                                ribbonSubComponents = listOf<IRibbonSubComponent>(
                                    RibbonButton(
                                        name="Button2",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    ),
                                    RibbonButton(
                                        name="button3",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    )
                                )
                            ),
                            RibbonGroup(
                                name = "group3",
                                idealSize = RibbonGroup.RibbonComponentSize.Large,
                                ribbonSubComponents = listOf<IRibbonSubComponent>(
                                    RibbonButton(
                                        name="button4",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    ),
                                    RibbonButton(
                                        name="button5",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    ),
                                    RibbonButton(
                                        name="button6",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    )
                                ),
                                sizeDefinition = RibbonGroup.SizeDefinition.ThreeButtons
                            ),
                            RibbonGroup(
                                name = "group4",
                                idealSize = RibbonGroup.RibbonComponentSize.Large,
                                ribbonSubComponents = listOf<IRibbonSubComponent>(
                                    RibbonButton(
                                        name="button7",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    ),
                                    RibbonButton(
                                        name="button8",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    ),
                                    RibbonButton(
                                        name="button9",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    ),
                                    RibbonButton(
                                        name="button10",
                                        imageLarge = Res.drawable.noteL,
                                        imageSmall = Res.drawable.noteS,
                                        onClick = {}
                                    )
                                ),
                                sizeDefinition = RibbonGroup.SizeDefinition.FourButtons
                            )
                        ),
                            listOf<RibbonTabComponents.Scale>(
                                RibbonTabComponents.Scale("group1",RibbonGroup.RibbonComponentSize.Large),
                                RibbonTabComponents.Scale("group2",RibbonGroup.RibbonComponentSize.Medium),
                                RibbonTabComponents.Scale("group4",RibbonGroup.RibbonComponentSize.Medium),
                                RibbonTabComponents.Scale("group3",RibbonGroup.RibbonComponentSize.Medium),
                                RibbonTabComponents.Scale("group4",RibbonGroup.RibbonComponentSize.Small),
                            )
                    )

                    )
                }
                1 -> { Text("This is insert!")}
                2 -> { Text("This is draw!")}
                3 -> { Text("This is design!")}
            }
        }
            }
    }
}