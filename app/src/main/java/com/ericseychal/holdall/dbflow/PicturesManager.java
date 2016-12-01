package com.ericseychal.holdall.dbflow;

import android.content.Context;
import android.util.Log;

import com.ericseychal.holdall.listpicture.Pictures;
import com.ericseychal.holdall.listpicture.Pictures_Table;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by ericseychal on 30/11/2016.
 */

public class PicturesManager {

    public PicturesManager(Context context) {
        FlowManager.init(new FlowConfig.Builder(context).openDatabasesOnInit(true).build());
    }

    public void save(Pictures pictures) {
        try {
            pictures.save();
        } catch (Exception e) {
            Log.w("SavePictures", e.toString());
        }
    }

    public List<Pictures> getAll() {
        return SQLite.select()
                .from(Pictures.class)
                .queryList();
    }

    public Pictures getByUrl(String url) {
        return SQLite.select()
                .from(Pictures.class)
                .where(Pictures_Table.url.eq(url))
                .querySingle();
    }

    public void delete(Pictures pictures) {
        pictures.delete();
    }

}
