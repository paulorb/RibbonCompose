package org.example.project

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.SubcomposeLayout
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


//THIS ONE!!! IMPLEMENT HERE!
@OptIn(ExperimentalUuidApi::class)
@Composable
fun RibbonTabBody(ribbonTabComponents: RibbonTabComponents) {
    SubcomposeLayout(modifier = Modifier.background(RibbonConfiguration.colorPattern.groupBackgroundColor)) { constraints ->

        // 1 - Build all elements with its ideal size
        // 2 - Measure all of them
        val idealSizePlaceables = subcompose("IdealSize") {
            ribbonTabComponents.compose()
        }.map { it.measure(constraints) }

        var measurementResult : MeasureResult = layout(constraints.maxWidth, constraints.maxHeight){}

        val idealSizePlaceablesHeight = idealSizePlaceables.maxOf { it.height }

        //3 - If fits place all of them - END
        if(idealSizePlaceables.sumOf { it.width } <= constraints.maxWidth) {
            //Place
            layout(constraints.maxWidth, idealSizePlaceablesHeight) {
                // Place the title at the top
                var xOffset = 0
                idealSizePlaceables.forEach { placeable ->
                    placeable.placeRelative(xOffset, 0 )
                    xOffset += placeable.width
                }
            }

        }else{
            //4 - If not, then verify the resizing order, get the first element of the resize order (stack)
            //and build it again now using its resized order and measure only this newly created compoenent
            ribbonTabComponents.resetScaledIndex()
            var notFit = true
            while(notFit &&  ribbonTabComponents.hasMoreScaleRules()){
                val resizedPlaceables = subcompose("Resized" + Uuid.random().toString()) {
                    ribbonTabComponents.composeScaled()
                }.map { it.measure(constraints) }
                val resizedPlaceablesHeight = resizedPlaceables.maxOf { it.height }
                if(resizedPlaceables.sumOf { it.width } <= constraints.maxWidth) {
                    notFit = false
                    //Place
                    measurementResult = layout(constraints.maxWidth, resizedPlaceablesHeight) {
                        // Place the title at the top
                        var xOffset = 0
                        resizedPlaceables.forEach { placeable ->
                            placeable.placeRelative(xOffset, 0 )
                            xOffset += placeable.width
                        }
                    }
                }
            }
            if(notFit){
                measurementResult
            }else
                measurementResult







        }

        // Measure the content depending on the title size
        // val bodyPlaceables = subcompose("Body") {
        //     Column {
        //         Text("Body text that depends on the title height")
        //         Text("Title height: $idealSizePlaceablesHeight px")
        //     }
        //  }.map { it.measure(constraints) }




    }

}