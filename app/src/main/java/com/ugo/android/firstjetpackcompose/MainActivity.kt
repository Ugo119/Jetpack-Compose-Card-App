package com.ugo.android.firstjetpackcompose

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ugo.android.firstjetpackcompose.ui.theme.FirstJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val infiniteTransition = rememberInfiniteTransition()
            var setSize by remember{
                mutableStateOf(200.dp)
            }
            val size by animateDpAsState(
                targetValue = setSize,
                tween(
                    delayMillis = 300,
                    durationMillis = 4000,
                    easing = LinearOutSlowInEasing
                )
            )
            val color by infiniteTransition.animateColor(
                initialValue = Color.Red,
                targetValue = Color.Cyan,
                animationSpec = infiniteRepeatable(
                    tween(durationMillis = 2000),
                    repeatMode = RepeatMode.Reverse
                )
            )

            Box(
                modifier = Modifier
                    .size(size)
                    .background(color),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    setSize += 100.dp
                }) {
                    Text(text = "Increase Box")
                }

            }

        }
    }
}







