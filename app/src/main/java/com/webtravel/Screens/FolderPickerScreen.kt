package com.webtravel.Screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webtravel.Structures.NavBar
import com.webtravel.Structures.Screen
import com.webtravel.WebViewJava.Screenshot
import java.io.File
import java.nio.file.Files

import java.nio.file.Paths
import kotlin.concurrent.thread


@Composable
@Preview(showBackground = true)
fun FolderPickerScreenPreview(){
    FolderPickerScreen(rememberNavController())
}

@Composable
fun FolderPickerScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()){
        PopupDesign(navController)
        NavBar(navController)
    }
}

lateinit var context: Context
var stringPath =""
@Composable
fun Demo_ExposedDropdownMenuBox() {
    context = LocalContext.current
    val context = LocalContext.current.applicationContext.dataDir
    stringPath = context.toPath().toString()+"/Sources"
    val path = Paths.get(stringPath)
    if(!Files.isDirectory(path)){
        Files.createDirectory(path)
    }
    val files = File(stringPath).list()
    if (files != null) {
        if(files.isNotEmpty()){
            DropDown(files)
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(files: Array<String>){
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(files[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor(),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            files.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        folderName = item
                    }
                )
            }
        }
    }
}


var folderName =""
var chaptername=""

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun PopupDesign(navController: NavController){
    Log.w("path of my app/folder",LocalContext.current.applicationInfo.dataDir)
    var textSource by remember { mutableStateOf("") }
    var textChapter by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column (modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .fillMaxHeight(0.95f),
        verticalArrangement = Arrangement.SpaceEvenly){
        Row( horizontalArrangement = Arrangement.Center, modifier =  Modifier.fillMaxWidth()) {
            Text(text = "Pick or Write the name of the new Source",style = TextStyle(color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold))
        }
        Row( horizontalArrangement = Arrangement.Center, modifier =  Modifier.fillMaxWidth()) {
            Demo_ExposedDropdownMenuBox()
        }
        Row( horizontalArrangement = Arrangement.Center, modifier =  Modifier.fillMaxWidth()) {
            TextField(value = textSource, onValueChange = { if(!it.contains("\n")){textSource = it }}
                ,placeholder = { Text("Folder name")},
                modifier = Modifier.onKeyEvent{
                if (it.key == Key.Enter) {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    if(textSource!=""){
                        folderName = textSource
                    }
                }
                return@onKeyEvent true
            }, singleLine = true,keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        if(textSource!=""){
                            folderName = textSource
                        }
                    }))
        }
        Row( horizontalArrangement = Arrangement.Center, modifier =  Modifier.fillMaxWidth()) {
            Text(text = "Please write down the Chapter number or name",style = TextStyle(color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold))
        }
        Row( horizontalArrangement = Arrangement.Center, modifier =  Modifier.fillMaxWidth()) {
            TextField(value = textChapter, onValueChange = {if(!it.contains("\n")){textChapter = it }},placeholder = { Text("Chapter name")},
                modifier = Modifier.onKeyEvent{
                    if (it.key == Key.Enter) {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        if(textChapter!=""){
                            chaptername = textChapter
                        }
                    }
                    return@onKeyEvent true
                }, singleLine = true,keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        if(textChapter!=""){
                            chaptername = textChapter
                        }
                    })
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {navController.navigate(Screen.Download.route) }){
                Text(text =  "Cancel")
            }
            Button(onClick = {
                navController.navigate(Screen.Waiting.route)
                createSourceFolder()
                Log.i("info","Thread has Started")
                navController.navigate(Screen.Home.route)
            }) {
                Text(text = "Download")
            }
        }
    }
}

fun createSourceFolder(){
    val newpath = stringPath +"/"+ folderName
    val path = Paths.get(newpath)
    if(!Files.isDirectory(path)){
        Files.createDirectory(path)
    }
    val createChapterPath = newpath+"/"+ chaptername
    val chapterPath = Paths.get(createChapterPath)
    if(!Files.isDirectory(chapterPath)){
        Files.createDirectory(chapterPath)
        val screenshot = Screenshot(webView,createChapterPath)
        screenshot.ScreenshotOrganizer()
    }
    else{
        Toast.makeText(context, "Error that Chapter Already exists", Toast.LENGTH_SHORT).show()
    }
}

