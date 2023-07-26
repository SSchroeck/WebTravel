package com.webtravel.Structures

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.webtravel.Screens.ChapterViewScreen
import com.webtravel.Screens.ContentVisualizerScreen
import com.webtravel.Screens.DownloadScreen
import com.webtravel.Screens.FolderPickerScreen
import com.webtravel.Screens.HomeScreen

import com.webtravel.Screens.SettingScreen
import com.webtravel.Screens.WaitingScreen
import com.webtravel.Screens.createSourceFolder

@Composable
fun SetupNavGraph( navController: NavHostController){
    NavHost(navController = navController, startDestination =  Screen.Download.route){
        composable( route = Screen.Home.route){
            HomeScreen(navController)
        }
        composable(route = Screen.Download.route){
            DownloadScreen(navController)
        }
        composable(route = Screen.Setting.route){
            SettingScreen(navController)
        }
        composable(route = Screen.ContentVisualizer.route){
           ContentVisualizerScreen(navController)
        }
        composable(route = Screen.FilePicker.route){
            FolderPickerScreen(navController)
        }
        composable(route = Screen.ChapterView.route){
            ChapterViewScreen(navController)
        }
        composable(route =Screen.Waiting.route){
            WaitingScreen(navController)
        }
    }
}