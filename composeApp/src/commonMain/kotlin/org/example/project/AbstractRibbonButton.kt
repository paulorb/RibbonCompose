package org.example.project

import org.jetbrains.compose.resources.DrawableResource

abstract class AbstractRibbonButton(
    private val name: String,
    private val imageLarge: DrawableResource,
    private val imageSmall: DrawableResource
) : IRibbonSubComponent {
    override fun getName(): String = name
}