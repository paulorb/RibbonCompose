package org.example.project

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.DrawerDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hoverEffect

class RibbonGroup(
    private val name: String,
    private val idealSize: RibbonComponentSize = RibbonComponentSize.Medium,
    private val ribbonSubComponents: List<IRibbonSubComponent>,
    private val sizeDefinition: SizeDefinition
) {
    private var scaleSize: RibbonComponentSize = RibbonComponentSize.Small

    enum class RibbonComponentSize {
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
        const val SCALE_ORDER_ANY = -1
    }

    fun deepCopy(): RibbonGroup {
        return RibbonGroup(this.name, this.idealSize, this.ribbonSubComponents, this.sizeDefinition)
    }

    fun setScaleSize(size: RibbonComponentSize) {
        scaleSize = size
    }

    fun getScaleSize() = scaleSize
    fun getName() = name  //name must be unique


    @Composable
    fun groupDivider() {
        SubcomposeLayout { constraints ->

            val measureRequiredSpace = subcompose("groupDivider") {
                //worst case (in terms of height) are 3 buttons scenario
                //TODO: when adding the name below the group this needs to be modified
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("placeholder")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("placeholder")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("placeholder")
                    }

                    RibbonGroupLabel("group label")


                }

            }.map { it.measure(constraints) }

            val dividerPlaceable = subcompose("groupDividerContent") {
                Divider(
                    color = RibbonConfiguration.colorPattern.dividerColor,
                    modifier = Modifier
                        .width(1.dp) // Thickness
                        .height(measureRequiredSpace.maxOf { it.height }.toDp() - 7.dp)
                )
            }.map { it.measure(constraints) }

            layout(2, measureRequiredSpace.maxOf { it.height }) {
                dividerPlaceable.forEach { placeable -> placeable.place(x = 0, y = 5) }
            }
        }
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun oneButton() {
        if (scaleSize != RibbonComponentSize.Large) {
            throw IllegalArgumentException("Only large RibbonComponentSize are supported for OneButton size definition")
        }

        Column(
            modifier = Modifier.padding(0.dp).clickable { ribbonSubComponents.first().onClickEvent() }
                .hoverEffect(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ribbonSubComponents.first().compose(RibbonComponentSize.Large)
        }


    }

    @Composable
    fun twoButtons(scaleSize: RibbonComponentSize) {
        if(scaleSize == RibbonComponentSize.Large){
            Row {
                ribbonSubComponents.forEachIndexed {  index, subComponent->
                    if(index != ribbonSubComponents.count() -1)
                        Column(modifier = Modifier.padding(end = 8.dp).hoverEffect(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }
                    else
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.hoverEffect()) {
                            subComponent.compose(scaleSize)
                        }

                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            ribbonSubComponents.forEach { subComponent ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect()) {
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
                        Column(modifier = Modifier.padding(end = 8.dp).hoverEffect(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }
                    else
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.hoverEffect()) {
                            subComponent.compose(scaleSize)
                        }

                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            ribbonSubComponents.forEach { subComponent ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect())  {
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
                        Column(modifier = Modifier.padding(end = 8.dp).hoverEffect(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                            subComponent.compose(scaleSize)
                        }
                    else
                        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.hoverEffect()) {
                            subComponent.compose(scaleSize)
                        }

                }
            }
        }
        else if(scaleSize == RibbonComponentSize.Medium){
            Row {
                Column(modifier = Modifier.padding(end = 8.dp).hoverEffect(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    ribbonSubComponents[0].compose(RibbonComponentSize.Large)
                }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect()) {
                        ribbonSubComponents[1].compose(RibbonComponentSize.Medium)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect()) {
                        ribbonSubComponents[2].compose(RibbonComponentSize.Medium)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect()) {
                        ribbonSubComponents[3].compose(RibbonComponentSize.Medium)
                    }
                }
            }
        }else if(scaleSize == RibbonComponentSize.Small){
            Row {
                Column(modifier = Modifier.padding(end = 8.dp).hoverEffect(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    ribbonSubComponents[0].compose(RibbonComponentSize.Large)
                }
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect()) {
                        ribbonSubComponents[1].compose(RibbonComponentSize.Small)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect()) {
                        ribbonSubComponents[2].compose(RibbonComponentSize.Small)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.hoverEffect()) {
                        ribbonSubComponents[3].compose(RibbonComponentSize.Small)
                    }
                }
            }
        }
    }

    @Composable
    fun RibbonGroupLabel(groupLabelValue: String) {
        Text(
            groupLabelValue,
            fontSize = 10.sp,
            fontFamily = FontFamily.SansSerif,
            color = RibbonConfiguration.colorPattern.fontColor
        )
    }

    @Composable
    fun RibbonGroupBody(body: @Composable () -> Unit) {
        val groupLabelValue = this.name
        SubcomposeLayout(modifier = Modifier.background(RibbonConfiguration.colorPattern.groupBackgroundColor)) { constraints ->
            val dividerPlaceable = subcompose("divider", content = { groupDivider() })
                .map {
                    it.measure(constraints)
                }

            val bodyPlaceable = subcompose("body", content = body)
                .map {
                    it.measure(constraints)
                }

            val groupLabelPlaceable = subcompose("groupLabel", content = {
                RibbonGroupLabel(groupLabelValue)
            }).map {
                it.measure(constraints)
            }

            //Height will be the height of the height of the divider
            val layoutTotalHeight = dividerPlaceable.maxOf { placeable -> placeable.height }

            val layoutTotalWidth = bodyPlaceable.maxOf { placeable -> placeable.width }

            layout(layoutTotalWidth, layoutTotalHeight) {
                bodyPlaceable.first().place(x = 0, y = 0)
                groupLabelPlaceable.first().place(
                    x = (layoutTotalWidth - groupLabelPlaceable.first().width) / 2,
                    y = (layoutTotalHeight - groupLabelPlaceable.first().height) - 5
                )
            }
        }
    }

    @Composable
    fun compose(scaleSize: RibbonComponentSize = this.idealSize) {
        val groupName = this.name
        when (this.sizeDefinition) {
            SizeDefinition.OneButton -> {
                RibbonGroupBody {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        oneButton()
                    }
                }


            }

            SizeDefinition.TwoButtons -> {
                RibbonGroupBody {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        twoButtons(scaleSize)
                    }
                }
            }

            SizeDefinition.ThreeButtons -> {
                RibbonGroupBody {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        threeButtons(scaleSize)
                    }
                }
            }

            SizeDefinition.FourButtons -> {
                RibbonGroupBody {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        fourButtons(scaleSize)
                    }
                }
            }

            SizeDefinition.FiveOrSixButtons -> {

            }
        }
        groupDivider()


    }

}
