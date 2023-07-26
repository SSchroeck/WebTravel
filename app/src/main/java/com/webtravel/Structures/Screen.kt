package com.webtravel.Structures

sealed class Screen (val route:String) {
    object Home : Screen(route = "home_screen")
    object Download : Screen(route = "download_screen")
    object Setting : Screen(route = "setting_screen")
    object ContentVisualizer: Screen(route ="content-visualizer_screen" )
    object FilePicker: Screen(route = "folder-picker_screen")
    object ChapterView: Screen(route= "chapter-view_screen")
    object Waiting: Screen(route = "waiting_screen")
}

