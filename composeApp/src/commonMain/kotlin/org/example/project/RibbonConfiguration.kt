package org.example.project

import androidx.compose.ui.graphics.Color


data class RibbonColorPattern(var groupBackgroundColor: Color, var tabRowBackgroundColor: Color, val fontColor: Color, val dividerColor: Color, val hoverButtonColor: Color)

class RibbonConfiguration {
    companion object {
        var darkMode = true
        var lightModeColorPattern = RibbonColorPattern(
            Color(245, 246, 247),
            Color(245, 246, 247),
            Color.Black,
            Color(231, 232, 232),
            Color(61,61,61)
        )
        var darkModeColorPattern = RibbonColorPattern(
            Color(41, 41, 41),
            Color(41, 41, 41),
            Color.White,
            Color(87,87,87),
            Color(61,61,61)
        )

        val colorPattern : RibbonColorPattern
            get()  {
                return if(darkMode)
                    darkModeColorPattern
                else
                    lightModeColorPattern
            }

    }

}