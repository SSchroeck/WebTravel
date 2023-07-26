package com.webtravel.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webtravel.Structures.NavBar
import com.webtravel.Structures.Screen
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@Composable
fun WaitingScreen(navController:NavController){
    Column(modifier = Modifier.fillMaxSize(),
        Arrangement.Center) {
        Row(modifier = Modifier
            .fillMaxWidth(),Arrangement.Center){
            Text(text = "please wait until we have finished processing your request")
        }
    }
}
@Composable
@Preview(showBackground = true)
fun WaitingScreenPreview(){
    WaitingScreen(navController = rememberNavController())
}