package com.webtravel.WebViewJava;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class Processing implements Runnable{
    Bitmap bitmap;

    File folder;

    public Processing(Bitmap bitmap, File folder){

        this.bitmap = bitmap;
        this.folder = folder;


    }

    public void run(){
        splitAndSaveBigBitmap();
    }
    // can be used instead of splitAndSaveBigBitmap() but the name should be warning enough
    private void bigHeapRequired(){
        LinkedList<Bitmap> bitmaps = splitBigBitmap();
        save(bitmaps);
    }

    private LinkedList<Bitmap> splitBigBitmap(){
        LinkedList <Bitmap> bitmaps = new LinkedList<>();
        int goalPixelHeight = bitmap.getHeight();
        int pixelHeightIndex =0;
        int pixelHeightDifference = goalPixelHeight - pixelHeightIndex;
        int allowedPixelHeight =3000;
        int allowedPixelWidth =1440;
        if(goalPixelHeight < allowedPixelHeight * 100){ //if i can split the Big canvas in more than 100 smaller screenshots we abort the mission
            if(pixelHeightDifference>allowedPixelHeight){
                do {
                    Bitmap bitmapLoop = Bitmap.createBitmap(bitmap,0,pixelHeightIndex,allowedPixelWidth,allowedPixelHeight);
                    bitmaps.add(bitmapLoop);

                    pixelHeightIndex = pixelHeightIndex +allowedPixelHeight;
                    pixelHeightDifference = goalPixelHeight -pixelHeightIndex;
                    if(pixelHeightDifference <= allowedPixelHeight){
                        Bitmap bitmapEnd =Bitmap.createBitmap(bitmap,0,pixelHeightIndex,allowedPixelWidth,pixelHeightDifference);
                        bitmaps.add(bitmapEnd);
                        pixelHeightIndex = pixelHeightIndex +pixelHeightDifference;
                        pixelHeightDifference = goalPixelHeight - pixelHeightIndex;
                    }
                }while (pixelHeightDifference>0);
            }
            else{
                bitmaps.add(bitmap);
            }
        }
        return bitmaps;
    }
    private void save (LinkedList <Bitmap> bitmaps){
        try{
            int index = 0;
            for(Bitmap bitmap:bitmaps){
                File picture = new File(folder,index+".png");
                OutputStream outputStream = Files.newOutputStream(picture.toPath());
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                outputStream.flush();
                outputStream.close();
                index+=1;

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void splitAndSaveBigBitmap(){
        int index = 0;
        int goalPixelHeight = bitmap.getHeight();
        int pixelHeightIndex =0;
        int pixelHeightDifference = goalPixelHeight - pixelHeightIndex;
        int allowedPixelHeight = 3000;
        int allowedPixelWidth = 1440;
        if (bitmap.getWidth() < allowedPixelWidth){
            allowedPixelWidth = bitmap.getWidth();
        }

        if(goalPixelHeight < allowedPixelHeight * 100){ //if i can split the Big canvas in more than 100 smaller screenshots we abort the mission
            if(pixelHeightDifference>allowedPixelHeight){
                do {
                    Bitmap bitmapLoop = Bitmap.createBitmap(bitmap,0,pixelHeightIndex,allowedPixelWidth,allowedPixelHeight);
                    save(bitmapLoop,index);
                    index++;
                    pixelHeightIndex = pixelHeightIndex +allowedPixelHeight;
                    pixelHeightDifference = goalPixelHeight -pixelHeightIndex;
                    if(pixelHeightDifference <= allowedPixelHeight){
                        Bitmap bitmapEnd =Bitmap.createBitmap(bitmap,0,pixelHeightIndex,allowedPixelWidth,pixelHeightDifference);
                        save(bitmapEnd,index);
                        pixelHeightIndex = pixelHeightIndex +pixelHeightDifference;
                        pixelHeightDifference = goalPixelHeight - pixelHeightIndex;
                    }
                }while (pixelHeightDifference>0);
            }
            else{
                save(bitmap,index);
            }
        }
    }
    private void save(Bitmap bitmap,int index) {
        try {
            File picture = new File(folder, index + ".png");
            OutputStream outputStream = Files.newOutputStream(picture.toPath());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    //TODO DEAD CODE
    private String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }

}
