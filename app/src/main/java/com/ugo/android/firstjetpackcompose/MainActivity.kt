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
import kotlinx.coroutines.launch

//Side effects are those things, though within a compose function but not a composable and have nothing
// to do with compose eg incrementing a counter or making network calls. They are those things that
// are not within scope of a function.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //CounterComposable()
            ButtonPress()
        }
    }
}

//When you need to run a suspend function within the scope of a composable function, you use
//LaunchedEffect, which is a composable function. When the LaunchedEffect is in composition,
//it launches a coroutine and the coroutine is cancelled when the LaunchedEffect leaves the
//composition.
@Composable
fun CounterComposable() {
    val scaffoldState = rememberScaffoldState()
    var counter by remember{
        mutableStateOf(0)
    }

    Scaffold(
        modifier = Modifier
        .fillMaxSize(),
        scaffoldState = scaffoldState) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {
            Button(onClick = {
                counter++
                Log.e(TAG, "CounterComposable: $counter")
            }) {
                Text(text = "Click me! $counter")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                if (counter > 0) {
                    counter--
                }

                Log.e(TAG, "CounterComposable: $counter")
            }) {
                Text(text = "Click me! $counter")
            }
        }

        if (counter > 0 && counter % 5 == 0) {
            Log.e(TAG, "CounterComposable_CounterINSIDE:  $counter")
            LaunchedEffect(scaffoldState.snackbarHostState){
                scaffoldState.snackbarHostState.showSnackbar("Item $counter")
            }
        }
    }
}

//When you need to obtain a composition-aware scope to launch a coroutine outside a composable,
// you can use rememberScaffoldState. rememberCoroutineScope is a composable function that
// returns a CoroutineScope bound to the point of the Composition where it's called.
// The scope will be cancelled when the call leaves the Composition.
@Composable
fun ButtonPress() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var name = "Ugochukwu"

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Hello $name")
            }
        }) {

        }
    }
}

//rememberUpdatedState: reference a value in an effect that shouldn't restart if the value changes
//LaunchedEffect restarts when one of the key parameters changes. However, in some situations you
// might want to capture a value in your effect that, if it changes, you do not want the effect
// to restart. In order to do this, it is required to use rememberUpdatedState to create a reference
// to this value which can be captured and updated.






