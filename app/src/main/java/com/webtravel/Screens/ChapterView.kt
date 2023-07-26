package com.webtravel.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webtravel.R
import com.webtravel.Structures.NavBar
import com.webtravel.Structures.Screen
import java.io.File

@Composable
@Preview(showBackground = true)
fun ChapterViewScreenPreview(){
    ChapterViewScreen(rememberNavController())
}

@Composable
fun ChapterViewScreen(navController: NavController){
    var tmp = LocalContext.current.applicationContext.dataDir.toPath().toString()+"/Sources"+"/"+ nameChapterView
    val files = File(tmp).list()
    Column(modifier = Modifier.fillMaxSize(),
        Arrangement.SpaceBetween) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)){
            ChapterFolderStructure(navController,files,tmp)
        }
        NavBar(navController)
    }
}

@Composable
fun ChapterFolderStructure(navController:NavController, files: Array<String>,tmp:String){
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)){
        items(files){
            FileVisualiserChildren(ParentPath = tmp,name = it, navController = navController)
        }
    }
}
var FullCallPathContentVisualizer:String =""
@Composable
fun FileVisualiserChildren(ParentPath:String,name: String, navController:NavController){
    Card(modifier = Modifier
        .clickable {
            navController.navigate(route = Screen.ContentVisualizer.route)
            FullCallPathContentVisualizer = ParentPath + "/" + name
        }
        .padding(horizontal = 8.dp, vertical = 8.dp)
        .fillMaxWidth(), shape = RoundedCornerShape(corner = CornerSize(16.dp))) {
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(40.dp),Arrangement.SpaceBetween) {
            Row() {
                Image(painter = painterResource(id = R.drawable.icons_folder),contentDescription = "Image",
                    modifier = Modifier
                        .width(30.dp)
                        .height(40.dp)
                        .padding(end = 8.dp))
                Text(text = name,modifier = Modifier.padding(top = 5.dp),
                    style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                )
            }
            Row() {
                Button(onClick = {
                    val  file = File(ParentPath + "/" + name)
                    deleteDirectory(file.toPath())
                    navController.navigate(Screen.ChapterView.route)
                },Modifier.width(10.dp)) {}
            }

        }
    }
}