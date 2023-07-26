package com.webtravel.Structures

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.webtravel.Screens.FullCallPathContentVisualizer
import java.io.File
import java.nio.charset.Charset
import kotlin.jvm.internal.Intrinsics.Kotlin
import kotlin.properties.Delegates

object Settings {
    lateinit var textURL:String
    var javaScriptEnabled by Delegates.notNull<Boolean>()
    var safeBrowsingEnabled by Delegates.notNull<Boolean>()
    var javaScriptCanOpenWindowsAutomatically by Delegates.notNull<Boolean>()
    var darkmode by Delegates.notNull<Boolean>()
    const val string = "/data/user/0/com.webtravel/files/Settings.txt"
    init {
        load();
    }
    private fun load(){
        val gson = Gson()
        val file = File(string)
        if( !file.exists()){
            textURL = "https://www.google.com/";
            javaScriptEnabled = true
            safeBrowsingEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            darkmode =true
            save()
        }
        val content = File(string).readText(charset = Charsets.UTF_16)
        val data = gson.fromJson(content,settingsJson::class.java)
        if (null != data){
            textURL = data.textURl
            javaScriptEnabled = data.javaScriptEnabled
            safeBrowsingEnabled = data.safeBrowsingEnabled
            javaScriptCanOpenWindowsAutomatically = data.javaScriptCanOpenWindowsAutomatically
            darkmode = data.darkmode
        }else{
            Log.e("Settings loader","Default settings loaded, be course normal settings couldn't be loaded")
            textURL = "https://www.google.com/";
            javaScriptEnabled = true
            safeBrowsingEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            darkmode =true
        }
    }
    fun save (){
        val data = settingsJson(textURL, javaScriptEnabled, safeBrowsingEnabled,javaScriptCanOpenWindowsAutomatically, darkmode)
        val gson =Gson()
        val content = gson.toJson(data)
        val file = File(string)
        if(file.exists()){
            file.delete()
        }
        file.createNewFile()
        File(string).writeText(content, charset = Charsets.UTF_16)
    }

}

data class settingsJson(
    val textURl:String,
    val javaScriptEnabled:Boolean,
    val safeBrowsingEnabled:Boolean,
    val javaScriptCanOpenWindowsAutomatically:Boolean,
    val darkmode:Boolean)