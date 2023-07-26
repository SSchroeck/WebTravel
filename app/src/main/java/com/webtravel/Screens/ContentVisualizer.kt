package com.webtravel.Screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.webtravel.Structures.NavBar
import java.io.File


@Composable
@Preview(showBackground = true)
fun ContentVisualizerPreview(){
    ContentVisualizerScreen(rememberNavController())
}

@Composable
fun ContentVisualizerScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.SpaceBetween
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)) {
            ContentSlider()
        }
        NavBar(navController)

    }
}

@Composable
fun ContentSlider(){
    val files = File(FullCallPathContentVisualizer).list()
    val option = BitmapFactory.Options()
    option.inPreferredConfig = Bitmap.Config.ARGB_8888
    LazyColumn(){
        items(files){
            var tmp = FullCallPathContentVisualizer +"/"+it
            Log.w("dumm",tmp)
            Image(painter = rememberAsyncImagePainter(tmp),
                contentDescription ="Image", modifier = Modifier.fillMaxWidth())
            var bitmap = BitmapFactory.decodeFile(tmp)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "content" )
        }
    }
}
