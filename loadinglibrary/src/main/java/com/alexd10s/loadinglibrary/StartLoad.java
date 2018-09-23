package com.alexd10s.loadinglibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by alex on 22/09/2018.
 */

public class StartLoad {
    private static final AtomicInteger nextId = new AtomicInteger();

    //private final LoadingLibrary loadingLibrary;
    private String url;
    Context context;
    Cache cache;


    public StartLoad(Context context, String url) {
        cache = new Cache();
        this.url = url;
        this.context = context;
    }

    /**
     * Method used to load an Image.
     */
    public void into(ImageView imageView){
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        Bitmap bitmap = getBitmapFromURL(url);
        imageView.setImageBitmap(bitmap);
    }

    /**
     * Method used to load a JSON file.
     */
    public String into(){
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        return getJson(url);
    }


    private String getJson(String jsonUrl) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(jsonUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
            }

            return buffer.toString();

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }




    private Bitmap getBitmapFromURL(String src) {
        //Check if is in cache
        final Bitmap bitmap = cache.getBitmapFromMemCache(src);

        if (bitmap != null) {
            return bitmap;
        }
        else {
            try {
                URL url = new URL(src);
                InputStream io = url.openConnection().getInputStream();

                /*
                 * Study the best practices to load an image in:
                 * https://developer.android.com/topic/performance/graphics/
                 */
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inScaled = true;
                bmOptions.inSampleSize = 6;

                Bitmap image = BitmapFactory.decodeStream(io, null, bmOptions);

                cache.addBitmapToMemoryCache(src, image);
                io.close();
                return image;
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }



}

