import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import org.example.project.RibbonConfiguration

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.hoverEffect(): Modifier {
    var isHovered by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        targetValue = if (isHovered)
            RibbonConfiguration.colorPattern.hoverButtonColor
        else
            RibbonConfiguration.colorPattern.groupBackgroundColor,
        animationSpec = tween(durationMillis = 300)
    )

    return this.background(backgroundColor, shape = RoundedCornerShape(2.dp))
        .onPointerEvent(PointerEventType.Enter) { isHovered = true }
        .onPointerEvent(PointerEventType.Exit) { isHovered = false }
}
