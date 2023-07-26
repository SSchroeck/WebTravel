package com.webtravel.WebViewJava;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.util.Log;
import android.webkit.WebView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Screenshot {
    File folder;
    WebView webView;
    public Screenshot(WebView webView, String path){
        this.webView = webView;
        folder = new File(path);
    }
    public void ScreenshotOrganizer(){
        Bitmap bitmap = terraScreenshot();
        Runnable r = new Processing(bitmap,folder);
        new Thread(r).start();
    }
    private Bitmap bigScreenshot(){
        // dont use  worse performence than HugeScreenshot() and same performence as  BigScreenshot()
        //webView.layout(0,0,webView.getMeasuredHeight(),webView.getMeasuredWidth());
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(webView.getMeasuredWidth(),webView.getContentHeight(),Bitmap.Config.ARGB_8888);
        Canvas bigCanvas = new Canvas(bitmap);
        Paint paint = new Paint();
        int iHeight = bitmap.getHeight();
        bigCanvas.drawBitmap(bitmap,0,iHeight,paint);
        webView.draw(bigCanvas);
        return bitmap;
    }
    private Bitmap hugeScreenshot(){
        Picture picture = webView.capturePicture();
        Bitmap bitmap = Bitmap.createBitmap(picture.getWidth(), picture.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        picture.draw(canvas);
        return bitmap;
    }
    private Bitmap gigaScreenshot(){
        // dont use  worse performence than HugeScreenshot() and same performence as  BigScreenshot()
        Bitmap bitmap = Bitmap.createBitmap(webView.getMeasuredWidth(),webView.getContentHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        webView.draw(canvas);
        return bitmap;
    }
    private Bitmap terraScreenshot(){
        long timeLog =System.currentTimeMillis();
        Picture picture = webView.capturePicture();
        Bitmap bitmap = Bitmap.createBitmap(picture.getWidth(), picture.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        webView.draw(canvas);
        timeLog -=System.currentTimeMillis();
        timeLog = timeLog/1000;
        Log.i("Screenshot test","TerraScreenshot() Time: "+timeLog+" in s. Hight readout"+bitmap.getHeight()+" readout webviewHeight "+webView.getMeasuredHeight()+" Bitmap size"+bitmap.getAllocationByteCount());

        return bitmap;
    }
    private void smallScreenshots(){
        // setting up variables
        int width = 1440;
        int height = 3000;
        Canvas canvas;
        int trueHeight = webView.capturePicture().getHeight();
        int times = (int)(trueHeight/height);
        if (webView.capturePicture().getWidth() < width){
            width = webView.capturePicture().getWidth();
        }
        //makes all screenshots tha are of equal height
        for (int i=0;i<times;i++){
            Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
            canvas = new Canvas(bitmap);
            Matrix matrix = new Matrix();
            matrix.setTranslate(0,-i*height);
            canvas.setMatrix(matrix);
            webView.draw(canvas);
            new Thread(new Save(bitmap,folder,i)).start();
        }
        if (((times*height)-trueHeight) > 0 && width > 0) {
            //handling the last unequal screenshot;
            Bitmap bitmap = Bitmap.createBitmap(width,(trueHeight-(times*height)),Bitmap.Config.RGB_565);
            canvas = new Canvas(bitmap);
            Matrix matrix = new Matrix();
            matrix.setTranslate(0,-times*height);
            canvas.setMatrix(matrix);
            webView.draw(canvas);
            new Thread(new Save(bitmap,folder,times)).start();
        }
    }

    private void testAndLog(){
        Log.i("Screenshot test","readout and testing of the screenshot functions");
        long timeLog =System.currentTimeMillis();
        Bitmap gbitmap= gigaScreenshot();
        timeLog -=System.currentTimeMillis();
        timeLog = timeLog/1000;
        Log.i("Screenshot test","gigantuaScreenshot() Time: "+timeLog+" in s. Hight readout"+gbitmap.getHeight()+" readout webviewHeight "+webView.getContentHeight());

        timeLog = 0;
        timeLog =System.currentTimeMillis();
        Bitmap hbitmap= hugeScreenshot();
        timeLog -=System.currentTimeMillis();
        timeLog = timeLog/1000;
        Log.i("Screenshot test","HugeScreenshot() Time: "+timeLog+" in s. Hight readout"+hbitmap.getHeight()+" readout webviewHeight "+webView.getContentHeight());
        timeLog = 0;
        timeLog =System.currentTimeMillis();
        Bitmap bbitmap=  bigScreenshot();
        timeLog -=System.currentTimeMillis();
        timeLog = timeLog/1000;
        Log.i("Screenshot test"," BigScreenshot() Time: "+timeLog+" in s. Hight readout"+bbitmap.getHeight()+" readout webviewHeight "+webView.getContentHeight());
        try {
            test(gbitmap,hbitmap,bbitmap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //will crash everything
    private void test(Bitmap gbitmap, Bitmap hbitmap, Bitmap bbitmap) throws IOException {
            String number = "1";
            String baselocation =  "/data/user/0/com.example.testsimonnavbar/Sources/Royalroad/1/";
            FileOutputStream fileOutputStream = null;
            fileOutputStream = new FileOutputStream(baselocation+"g"+number+".png");
        if (fileOutputStream!= null) {
            gbitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        }
        fileOutputStream = null;
        fileOutputStream = new FileOutputStream(baselocation+"h"+number+".png");
        if (fileOutputStream!= null) {
            hbitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        }
        fileOutputStream = null;
        fileOutputStream = new FileOutputStream(baselocation+"b"+number+".png");
        if (fileOutputStream!= null) {
            bbitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
        }
        Log.w("info", "done");
    }
}



