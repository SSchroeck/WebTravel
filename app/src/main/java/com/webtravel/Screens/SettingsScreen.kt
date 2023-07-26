package com.webtravel.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webtravel.R
import com.webtravel.Structures.NavBar
import com.webtravel.Structures.Screen
import com.webtravel.Structures.Settings


@Composable
fun SettingScreen(navController: NavController){
    Log.w("path of my app/setting",LocalContext.current.applicationInfo.dataDir)

    Column(modifier = Modifier.fillMaxSize(),
        Arrangement.SpaceBetween) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)){
            SettingsContent()
        }
        NavBar(navController)
    }
}
@Composable
@Preview(showBackground = true)
fun SettingScreenPreview(){
    SettingScreen(rememberNavController())
}
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SettingsContent() {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var textURL by remember { mutableStateOf(TextFieldValue(Settings.textURL))}
    var boolJavascript by remember { mutableStateOf(Settings.javaScriptEnabled) }
    var boolSafeBrowsing by remember { mutableStateOf(Settings.safeBrowsingEnabled) }
    var boolJavaScriptCanOpenWindowsAutomatically by remember { mutableStateOf(Settings.javaScriptCanOpenWindowsAutomatically) }
    var boolDarkmode by remember { mutableStateOf(Settings.darkmode) }



    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Card(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
            Column(verticalArrangement = Arrangement.SpaceAround) {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "StartURL")
                }
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        modifier= Modifier.fillMaxWidth().onKeyEvent{
                            if(it.key == Key.Enter){
                                keyboardController?.hide()
                                focusManager.clearFocus()
                                Settings.textURL = textURL.text
                                Settings.save()
                            }
                            Log.w("what am i",it.key.toString())
                            return@onKeyEvent true
                        },
                        value = textURL,
                        onValueChange = {
                            if(!it.text.contains("\n")){
                                textURL = it
                            }
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = {keyboardController?.hide()
                                focusManager.clearFocus()
                                Settings.textURL = textURL.text
                                Settings.save()
                            })
                    )
                }
            }
        }
        Card(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                    Text(text = "SafeBrowsing Enabled")
                }
                Switch(
                    checked = boolSafeBrowsing,
                    onCheckedChange = { boolSafeBrowsing = !boolSafeBrowsing
                    Settings.safeBrowsingEnabled = boolSafeBrowsing
                    Settings.save()
                    })

            }
        }
        Card(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                    Text(text = "JavaScript enabled")
                }
                Switch(
                    checked = boolJavascript,
                    onCheckedChange = { boolJavascript = !boolJavascript
                        Settings.javaScriptEnabled = boolJavascript
                        Settings.save()
                    })
            }
        }
        if(boolJavascript){
            Card(modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                        Text(text = "JavaScript Can Open\nWindows Automatically" )
                    }
                    Switch(checked = boolJavaScriptCanOpenWindowsAutomatically,
                        onCheckedChange = {
                            boolJavaScriptCanOpenWindowsAutomatically =
                                !boolJavaScriptCanOpenWindowsAutomatically
                            Settings.javaScriptCanOpenWindowsAutomatically = boolJavaScriptCanOpenWindowsAutomatically
                            Settings.save()
                        })
                }
            }
        }
        // had no idea how to implement
        /*Card(modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
               Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight()) {
                   Text(text = "Dark-mode")
               }
                Switch(checked = boolDarkmode,
                    onCheckedChange = { boolDarkmode = !boolDarkmode
                        Settings.darkmode = boolDarkmode
                        Settings.save()
                    }
                )
            }
        }*/
    }
}


