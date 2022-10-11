package ir.alishi.circleprogressanimate

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.alishi.circleprogressanimate.ui.theme.CircleProgressAnimateTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
fun VpnConnectButton(
    outStrokeWidth: Float = 8f,
    thumbStrokeWidth: Float = 6f,
) {

    var connected by remember {
        mutableStateOf(false)
    }
    var text by remember {
        mutableStateOf("Tap To Connect ")
    }

    var progress by remember {
        mutableStateOf(0)
    }

    var color by remember {
        mutableStateOf(Color(0xFFA30022))
    }

    val animateProgress by animateIntAsState(
        targetValue = progress,
        animationSpec = tween(1200)
    )

    val animateColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(1500)
    )

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        progress = 360
        delay(1300)
        progress = 0
    }

   CircleProgressAnimateTheme {
     Surface {
         Column(
             Modifier
                 .padding(10.dp)
                 .fillMaxSize(),
             horizontalAlignment = Alignment.CenterHorizontally,
             verticalArrangement = Arrangement.Center,
         ) {

             Surface(
                 shape = CircleShape,
                 color = Color.White,
                 elevation = 3.dp,
                 onClick = {

                     scope.launch {
                         if (!connected) {
                             connected = true
                             color = Color.DarkGray
                             text = "Connecting.. "
                             progress = 300
                             delay(1500)
                             progress = 325
                             delay(5000)
                             progress = 360
                             text = "Connected"
                             color = Color(0xFF008B48)

                         } else {
                             text = "Tap To Connect"
                             color = Color.DarkGray
                             progress = 0
                             connected = false
                         }

                     }
                 }
             ) {
                 Box(
                     contentAlignment = Alignment.Center,
                     modifier = Modifier.background(Color.White),
                 ) {

                     Canvas(
                         Modifier
                             .size(50.dp)
                             .padding(10.dp)
                     ) {
                         drawThumb(
                             color = animateColor,
                             thumbStrokeWidth = thumbStrokeWidth
                         )
                     }

                     Canvas(
                         Modifier
                             .size(120.dp)
                             .padding(10.dp)
                     ) {
                         drawCircleProgressBar(
                             color = animateColor,
                             progressStrokeWidth = outStrokeWidth,
                             sweepAngle = animateProgress.toFloat()
                         )
                     }
                 }
             }
             Spacer(modifier = Modifier.height(15.dp))
             Text(
                 text = text,
                 style = MaterialTheme.typography.overline.copy(
                     fontSize = 16.sp
                 )
             )
         }
     }
   }


}

fun DrawScope.drawThumb(
    thumbStrokeWidth: Float,
    color: Color = Color(0xFFA30022)
) {
    drawArc(
        color = color,
        startAngle = -60f,
        sweepAngle = 300f,
        useCenter = false,
        topLeft = Offset.Zero,
        style = Stroke(
            width = thumbStrokeWidth,
            cap = StrokeCap.Round,
        )
    )

    drawLine(
        color = color,
        start = center,
        end = Offset(
            x = center.x,
            y = Offset.Zero.y - 5 // because of padding
        ),
        cap = StrokeCap.Round,
        strokeWidth = thumbStrokeWidth
    )
}

fun DrawScope.drawCircleProgressBar(
    color: Color = Color.DarkGray,
    progressStrokeWidth: Float,
    sweepAngle: Float,
) {
    drawArc(
        color = Color.LightGray,
        startAngle = -90f,
        sweepAngle = 360f,
        useCenter = false,
        topLeft = Offset.Zero,
        style = Stroke(
            width = progressStrokeWidth,
            cap = StrokeCap.Round,
        )
    )

    drawArc(
        color = color,
        startAngle = -90f,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = Offset.Zero,
        style = Stroke(
            width = progressStrokeWidth + 3,
            cap = StrokeCap.Round,
        )
    )
}
