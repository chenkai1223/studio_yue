package com.example.vulcan.studio_yue.img;

import android.app.Application;
import android.os.Environment;

import com.example.vulcan.studio_yue.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * ImagerLoader
 */
public class MyImg extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        //路径ss
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/images");

        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .diskCache(new UnlimitedDiskCache(file))
                .build();



        ImageLoader instance = ImageLoader.getInstance();
        instance.init(imageLoaderConfiguration);

    }
}
