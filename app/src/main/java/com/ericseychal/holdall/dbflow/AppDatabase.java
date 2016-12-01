package com.ericseychal.holdall.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ericseychal on 30/11/2016.
 */

@Database( name = AppDatabase.NAME, version = AppDatabase.VERSION, foreignKeysSupported = true)
public class AppDatabase {
    public static final String NAME = "PicturesHistoric";
    public static final int VERSION = 4;
}
