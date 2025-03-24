package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


class RibbonButton(private val name: String, private val imageLarge: DrawableResource, private val imageSmall: DrawableResource, private val onClick: () -> Unit) : AbstractRibbonButton(name, imageLarge, imageSmall){
    @Composable
    override fun compose(scaleSize: RibbonGroup.RibbonComponentSize) {


        if(scaleSize == RibbonGroup.RibbonComponentSize.Large ) {
            //   Column {
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = painterResource(imageLarge),
                contentDescription = "Sample",
                contentScale = ContentScale.None
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("$name", fontSize = 12.sp, fontFamily = FontFamily.SansSerif, color = RibbonConfiguration.colorPattern.fontColor, modifier = Modifier.padding(horizontal = 2.dp))
            Spacer(modifier = Modifier.height(4.dp))
            //   }
        }
        if(scaleSize ==  RibbonGroup.RibbonComponentSize.Medium) {
            Spacer(modifier = Modifier.height(4.dp))
            Image(
                painter = painterResource(imageSmall),
                contentDescription = "Sample",
                contentScale = ContentScale.None
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("$name", fontSize = 12.sp, fontFamily = FontFamily.SansSerif, color = RibbonConfiguration.colorPattern.fontColor)
            Spacer(modifier = Modifier.height(4.dp))
        }
        if( scaleSize ==  RibbonGroup.RibbonComponentSize.Small) {
            //    Row {
            Image(
                painter = painterResource(imageSmall),
                contentDescription = "Sample",
                contentScale = ContentScale.None
            )

            //   }
        }
    }

    override fun onClickEvent() {
        this.onClick()
    }


}

