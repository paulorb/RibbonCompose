package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable

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
                ribbonSubComponents.forEach { subComponent ->
                    Column {
                        subComponent.compose(scaleSize)
                    }
                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            ribbonSubComponents.forEach { subComponent ->
                Row {
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
                ribbonSubComponents.forEach { subComponent ->
                    Column {
                        subComponent.compose(scaleSize)
                    }
                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            ribbonSubComponents.forEach { subComponent ->
                Row {
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
                ribbonSubComponents.forEach { subComponent ->
                    Column {
                        subComponent.compose(scaleSize)
                    }
                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            Row {
                Column {
                    ribbonSubComponents[0].compose(RibbonComponentSize.Large)
                }
                Column {
                    Row {
                        ribbonSubComponents[1].compose(RibbonComponentSize.Medium)
                    }
                    Row {
                        ribbonSubComponents[2].compose(RibbonComponentSize.Medium)
                    }
                    Row {
                        ribbonSubComponents[3].compose(RibbonComponentSize.Medium)
                    }
                }
            }
        }else if(scaleSize == RibbonComponentSize.Small){
            Row {
                Column {
                    ribbonSubComponents[0].compose(RibbonComponentSize.Large)
                }
                Column {
                    Row {
                        ribbonSubComponents[1].compose(RibbonComponentSize.Small)
                    }
                    Row {
                        ribbonSubComponents[2].compose(RibbonComponentSize.Small)
                    }
                    Row {
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
                Column {
                    oneButton()
                }
            }
            SizeDefinition.TwoButtons -> {
                Column {
                    twoButtons(scaleSize)
                }
            }
            SizeDefinition.ThreeButtons -> {
                Column {
                    threeButtons(scaleSize)
                }
            }
            SizeDefinition.FourButtons -> {
                Column {
                    fourButtons(scaleSize)
                }
            }
            SizeDefinition.FiveOrSixButtons -> {

            }
        }



    }

}
