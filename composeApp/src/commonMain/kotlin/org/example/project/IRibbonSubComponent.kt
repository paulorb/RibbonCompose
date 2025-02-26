package org.example.project

import androidx.compose.runtime.Composable

interface IRibbonSubComponent {
    fun getName(): String
    @Composable
    fun compose(scaleSize: RibbonGroup.RibbonComponentSize)
}