package ir.alishi.circleprogressanimate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ir.alishi.circleprogressanimate.ui.theme.CircleProgressAnimateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                VpnConnectButton()
            }
        }
    }
}

