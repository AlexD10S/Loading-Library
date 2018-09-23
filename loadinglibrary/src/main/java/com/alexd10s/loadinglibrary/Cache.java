package com.alexd10s.loadinglibrary;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by alex on 23/09/2018.
 * Developed studying the docs:
 * https://developer.android.com/topic/performance/graphics/cache-bitmap#java
 */

public class Cache {
    private LruCache<String, Bitmap> mMemoryCache;

    public Cache(){
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


}
