package com.ugo.android.firstjetpackcompose

import android.content.ContentValues.TAG
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.launch

//Side effects are those things, though within a compose function but not a composable and have nothing
// to do with compose eg incrementing a counter or making network calls. They are those things that
// are not within scope of a function.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterComposable()
        }
    }
}

@Composable
fun CounterComposable() {
    val scaffoldState = rememberScaffoldState()
    var counter = remember {
        0
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
        .fillMaxSize(),
        scaffoldState = scaffoldState) {

        Button(onClick = {
            counter++
            Log.e(TAG, "CounterComposable: $counter")
        }) {
            Text(text = "Click me! $counter")
        }
        if (counter > 0 && counter % 5 == 0) {
            LaunchedEffect(scaffoldState.snackbarHostState){
                scaffoldState.snackbarHostState.showSnackbar("Item $counter")
            }
        }

    }

}






