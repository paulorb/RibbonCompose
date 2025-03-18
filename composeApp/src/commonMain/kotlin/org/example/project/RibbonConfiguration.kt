package org.example.project

import androidx.compose.ui.graphics.Color


data class RibbonColorPattern(var groupBackgroundColor: Color, var tabRowBackgroundColor: Color, val fontColor: Color, val dividerColor: Color)

class RibbonConfiguration {
    companion object {
        var darkMode = true
        var lightModeColorPattern = RibbonColorPattern(
            Color(245, 246, 247),
            Color(245, 246, 247),
            Color.Black,
            Color(231, 232, 232)
        )
        var darkModeColorPattern = RibbonColorPattern(
            Color(51, 51, 51),
            Color(51, 51, 51),
            Color.White,
            Color(87,87,87)
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