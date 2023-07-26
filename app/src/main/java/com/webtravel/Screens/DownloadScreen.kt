package com.webtravel.Screens

import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.webtravel.R
import com.webtravel.Structures.NavBar
import com.webtravel.Structures.Screen
import com.webtravel.Structures.SearchBar
import com.webtravel.Structures.Settings
import com.webtravel.WebViewJava.MyWebViewClient

@Composable
fun DownloadScreen(navController: NavController){
    Log.w("path of my app/download", LocalContext.current.applicationInfo.dataDir)
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.065f)
            ) {
            SearchBar()
            PopupWindowDialog(navController)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.95f)){
            WebPage(Settings.textURL)
        }
       NavBar(navController)

    }
}
@Composable
@Preview(showBackground = true)
fun DownloadScreenPreview(){
    DownloadScreen(rememberNavController())
}
var webView:WebView? = null;
@Composable
fun WebPage(mUrl:String){

    WebView.enableSlowWholeDocumentDraw()
    if (webView == null){
        AndroidView(factory = {
            WebView(it).apply {
                WebView.enableSlowWholeDocumentDraw()
                settings.safeBrowsingEnabled = Settings.safeBrowsingEnabled
                settings.javaScriptEnabled = Settings.javaScriptEnabled
                settings.javaScriptCanOpenWindowsAutomatically = Settings.javaScriptCanOpenWindowsAutomatically
                webViewClient =MyWebViewClient()
                loadUrl(mUrl)
                webView = this
            }

        }, update = {
            it.loadUrl(mUrl)

        })

    }else{
        AndroidView(factory = { webView!! })
    }

}

@Composable
fun PopupWindowDialog(navController: NavController) {
    Button(onClick = {
            navController.navigate(Screen.FilePicker.route)
        }, modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
            shape = RoundedCornerShape(0.0f)) {

            Icon(painter = painterResource(id = R.drawable.icons_download),
                contentDescription = "DownloadIcon",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )

    }
}