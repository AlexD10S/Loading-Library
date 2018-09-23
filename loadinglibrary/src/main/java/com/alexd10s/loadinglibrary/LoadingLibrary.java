package com.alexd10s.loadinglibrary;


import android.content.Context;
import android.net.Uri;

/**
 * Created by alex on 22/09/2018.
 */

public class LoadingLibrary {
    private Context context;
    private static LoadingLibrary loadingLibrary;

    private LoadingLibrary(Context context) {
        this.context = context;
    }


    public static LoadingLibrary get(Context cntx) {
        if (loadingLibrary == null)
            loadingLibrary = new LoadingLibrary(cntx.getApplicationContext());

        return loadingLibrary;
    }

    public StartLoad load(String url){
        //Uri uri = Uri.parse(url);
        return new StartLoad(context, url);
    }



}
