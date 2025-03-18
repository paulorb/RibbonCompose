package org.example.project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


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
            val strokeWidth = 2 * density
            val y = size.height - strokeWidth /2

            drawLine(
                RibbonConfiguration.colorPattern.fontColor,
                Offset(0f,y),
                Offset(size.width, y),
                strokeWidth
            )
        }) {
            Text(name, fontWeight = FontWeight.Bold,modifier = modifier.padding(vertical = 4.dp), color = RibbonConfiguration.colorPattern.fontColor)
        }
    }else{
        Box(modifier = modifier.padding(horizontal = 16.dp).clickable{ onClick()}) {
            Text(name, modifier = modifier.padding(vertical = 4.dp), color = RibbonConfiguration.colorPattern.fontColor)
        }
    }
}