package com.webtravel.WebViewJava;

import android.graphics.Bitmap;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class Save implements Runnable{
        Bitmap bitmap;
        int number;
        File folder;

    public Save(Bitmap bitmap, File folder,int number){
            this.number = number;
            this.bitmap = bitmap;
            this.folder = folder;


        }

    @Override
    public void run() {
        save();
    }
    private void save() {
        try {
            File picture = new File(folder, number + ".png");
            OutputStream outputStream = Files.newOutputStream(picture.toPath());
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
