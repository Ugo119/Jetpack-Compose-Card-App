package com.ugo.android.firstjetpackcompose

import android.inputmethodservice.Keyboard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           val scaffoldState = rememberScaffoldState()
           var textFieldState by remember {
               mutableStateOf("")
           }
           val scope = rememberCoroutineScope()
           Scaffold(
               modifier = Modifier.fillMaxSize(),
               scaffoldState = scaffoldState
           ) {
               Column(
                   horizontalAlignment = Alignment.CenterHorizontally,
                   verticalArrangement = Arrangement.Center,
                   modifier = Modifier.fillMaxSize()
               ) {
                   TextField(
                       value = textFieldState ,
                       label = {
                           Text("Enter your name")
                       },
                       onValueChange = {
                           textFieldState = it
                       },
                       singleLine = true,
                       //modifier = Modifier.fillMaxWidth()
                   )
                   Spacer(modifier = Modifier.height(20.dp))
                   Button(
                       onClick = {
                           scope.launch {
                               scaffoldState.snackbarHostState.showSnackbar("Hello again, $textFieldState")
                           }
                       }) {
                       Text(text = "Click to greet")
                   }
               }

           }
        }
    }
}







