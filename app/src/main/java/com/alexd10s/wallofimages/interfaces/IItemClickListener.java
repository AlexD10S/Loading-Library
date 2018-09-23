package com.alexd10s.wallofimages.interfaces;

import android.view.View;

/**
 * Created by alex on 22/09/2018.
 */

public interface IItemClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
