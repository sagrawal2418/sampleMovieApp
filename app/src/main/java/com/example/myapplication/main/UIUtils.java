package com.example.myapplication.main;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;

public class UIUtils {
    private static final RequestOptions standardGlideOptions = new RequestOptions().placeholder(R.drawable.bg_product_default).error(R.drawable.bg_product_default).diskCacheStrategy(DiskCacheStrategy.ALL);


    public static void loadImageIntoView(String path, ImageView v) {
        Glide.with(v.getContext()).asBitmap().apply(standardGlideOptions).load(path)
                .transition(BitmapTransitionOptions.withCrossFade(500))
                .into(v);
    }
}