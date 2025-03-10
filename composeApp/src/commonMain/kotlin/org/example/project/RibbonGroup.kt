package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class RibbonGroup(
    private val name: String,
    private val idealSize: RibbonComponentSize = RibbonComponentSize.Medium,
    private val ribbonSubComponents: List<IRibbonSubComponent>,
    private val sizeDefinition: SizeDefinition
) {
    private var scaleSize: RibbonComponentSize = RibbonComponentSize.Small

    enum class RibbonComponentSize{
        Small,
        Medium,
        Large
    }

    enum class SizeDefinition {
        OneButton,
        TwoButtons,
        ThreeButtons,
        FourButtons,
        FiveOrSixButtons
    }

    companion object {
        const val SCALE_ORDER_ANY=-1
    }

    fun deepCopy() : RibbonGroup {
        return RibbonGroup(this.name, this.idealSize, this.ribbonSubComponents, this.sizeDefinition)
    }

    fun setScaleSize(size: RibbonComponentSize) {
        scaleSize = size
    }
    fun getScaleSize() = scaleSize
    fun getName() = name  //name must be unique

    @Composable
    fun oneButton() {
        if(scaleSize != RibbonComponentSize.Large) {
            throw IllegalArgumentException("Only large RibbonComponentSize are supported for OneButton size definition")
        }
        ribbonSubComponents.first().compose(RibbonComponentSize.Large)
    }

    @Composable
    fun twoButtons(scaleSize: RibbonComponentSize) {
        if(scaleSize == RibbonComponentSize.Large){
            Row {
                ribbonSubComponents.forEachIndexed {  index, subComponent->
                    if(index != ribbonSubComponents.count() -1)
                        Column(modifier = Modifier.padding(end = 8.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }
                    else
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }

                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            ribbonSubComponents.forEach { subComponent ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    subComponent.compose(scaleSize)
                }
            }
        }else{
            throw IllegalArgumentException("Only large and medium RibbonComponentSize are supported for TwoButtons size definition")
        }
    }

    @Composable
    fun threeButtons(scaleSize: RibbonComponentSize) {
        if(ribbonSubComponents.count() != 3) {
            throw IllegalArgumentException("threeButtons only accepts 3 buttons")
        }
        if(scaleSize == RibbonComponentSize.Large){
            Row {
                ribbonSubComponents.forEachIndexed {  index, subComponent->
                    if(index != ribbonSubComponents.count() -1)
                        Column(modifier = Modifier.padding(end = 8.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }
                    else
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }

                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            ribbonSubComponents.forEach { subComponent ->
                Row(verticalAlignment = Alignment.CenterVertically)  {
                    subComponent.compose(scaleSize)
                }
            }
        }else{
            throw IllegalArgumentException("Only large and medium RibbonComponentSize are supported for ThreeButtons size definition")
        }
    }

    @Composable
    fun fourButtons(scaleSize: RibbonComponentSize) {
        if(ribbonSubComponents.count() != 4) {
            throw IllegalArgumentException("fourButtons only accepts 4 buttons")
        }
        if(scaleSize == RibbonComponentSize.Large){
            Row {
                ribbonSubComponents.forEachIndexed {  index, subComponent->
                    if(index != ribbonSubComponents.count() -1)
                        Column(modifier = Modifier.padding(end = 8.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }
                    else
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }

                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            Row {
                Column(modifier = Modifier.padding(end = 8.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    ribbonSubComponents[0].compose(RibbonComponentSize.Large)
                }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ribbonSubComponents[1].compose(RibbonComponentSize.Medium)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ribbonSubComponents[2].compose(RibbonComponentSize.Medium)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ribbonSubComponents[3].compose(RibbonComponentSize.Medium)
                    }
                }
            }
        }else if(scaleSize == RibbonComponentSize.Small){
            Row {
                Column(modifier = Modifier.padding(end = 8.dp),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    ribbonSubComponents[0].compose(RibbonComponentSize.Large)
                }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ribbonSubComponents[1].compose(RibbonComponentSize.Small)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ribbonSubComponents[2].compose(RibbonComponentSize.Small)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ribbonSubComponents[3].compose(RibbonComponentSize.Small)
                    }
                }
            }
        }
    }


    @Composable
    fun compose(scaleSize: RibbonComponentSize = this.idealSize) {
        when(this.sizeDefinition){
            SizeDefinition.OneButton -> {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    oneButton()
                }
            }
            SizeDefinition.TwoButtons -> {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    twoButtons(scaleSize)
                }
            }
            SizeDefinition.ThreeButtons -> {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    threeButtons(scaleSize)
                }
            }
            SizeDefinition.FourButtons -> {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)  {
                    fourButtons(scaleSize)
                }
            }
            SizeDefinition.FiveOrSixButtons -> {

            }
        }



    }

}
