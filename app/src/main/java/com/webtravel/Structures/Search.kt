package com.webtravel.Structures

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.webtravel.Screens.webView


@Preview
@Composable
fun SearchBarSamplePreview(){
    SearchBar()
}
var lastWebsiteCall =""
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(

        value = text,
        onValueChange = { text = it },
        label = { Text("Search") },
        modifier = Modifier.fillMaxWidth(0.8f).onKeyEvent{
            if(it.key == Key.Enter){
                if (webView == null){
                    Log.w("help","webView is null")
                }
                keyboardController?.hide()
                focusManager.clearFocus()
                val a ="https://"
                lastWebsiteCall =text
                if(text.contains(a)){
                    webView?.loadUrl(text)
                }else{
                    webView?.loadUrl(a+text)
                }
                text=""
            }
            return@onKeyEvent true
        }, singleLine = true,keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {keyboardController?.hide()
                if (webView == null){
                    Log.w("help","webView is null")
                }
                keyboardController?.hide()
                focusManager.clearFocus()
                val a ="https://"
                lastWebsiteCall =text
                if(text.contains(a)){
                    webView?.loadUrl(text)
                }else{
                    webView?.loadUrl(a+text)
                }
                text=""
            })

    )
}

fun fixUrl(){
    val baseSearch = "https://www.google.com/search?q="
    if(!lastWebsiteCall.contains(baseSearch)){

        Log.w("info","Lastwebsite "+ lastWebsiteCall)
        Log.w("info","joined search "+baseSearch+ lastWebsiteCall)
        webView?.loadUrl(baseSearch+ lastWebsiteCall) //TODO first time it does not work

    }
}

