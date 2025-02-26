package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


class RibbonButton(private val name: String, private val imageLarge: DrawableResource, private val imageSmall: DrawableResource) : AbstractRibbonButton(name, imageLarge, imageSmall){
    @Composable
    override fun compose(scaleSize: RibbonGroup.RibbonComponentSize) {


        if(scaleSize == RibbonGroup.RibbonComponentSize.Large ) {
            //   Column {
            Image(
                painter = painterResource(imageLarge),
                contentDescription = "Sample",
                contentScale = ContentScale.None
            )
            Text("$name")
            //   }
        }
        if(scaleSize ==  RibbonGroup.RibbonComponentSize.Medium) {
            //    Row {
            Image(
                painter = painterResource(imageSmall),
                contentDescription = "Sample",
                contentScale = ContentScale.None
            )
            Text("$name")
            //   }
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

}

