package com.webtravel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.webtravel.Structures.Screen
import com.webtravel.Structures.Settings
import com.webtravel.Structures.SetupNavGraph
import com.webtravel.ui.theme.WebTravelTheme

class MainActivity : ComponentActivity() {
    lateinit var  navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebTravelTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)
                    navController.navigate(Screen.Home.route)
                }
            }
        }
    }
}

