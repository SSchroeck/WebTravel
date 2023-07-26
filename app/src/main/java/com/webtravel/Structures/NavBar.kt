package com.webtravel.Structures

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webtravel.R


@Composable
fun NavBar(navController: NavController){

    Row(modifier = Modifier
        .background(Color.DarkGray)
        .fillMaxWidth()
        .fillMaxHeight(), horizontalArrangement = Arrangement.SpaceEvenly) {
        // <a target="_blank" href="https://icons8.com/icon/2969/settings">Settings</a> icon by <a target="_blank" href="https://icons8.com">Icons8</a>

        ButtonI(R.drawable.icons_home,"Home", Screen.Home.route,navController)
        ButtonI(R.drawable.icons_download,"Download", Screen.Download.route, navController)
        ButtonI(R.drawable.icons_settings,"Settings", Screen.Setting.route, navController)
    }
}

@Composable
fun ButtonI(imageId: Int,text: String,route: String,navController: NavController){
    Button( onClick = {
               navController.navigate(route = route)
        //your onclick code here
    }, Modifier.padding(4.dp).fillMaxHeight(),shape = RoundedCornerShape(20.dp),elevation =  ButtonDefaults.buttonElevation(
        defaultElevation = 10.dp,
        pressedElevation = 15.dp,
        disabledElevation = 0.dp
    )
    ) {

        Image(
            painterResource(id = imageId),
            contentDescription ="Home icon",
            modifier = Modifier.padding(end = 5.dp))

        Text(text = ""+text)
    }
}


@Preview(showBackground = true)
@Composable
fun NavbarPreview() {
    Column() {
        Spacer(modifier = Modifier.fillMaxHeight(0.95f).fillMaxWidth())
        NavBar(navController = rememberNavController())
    }
}