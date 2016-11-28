package com.ericseychal.holdall.listpicture;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericseychal on 25/11/2016.
 */

public class FlickrService extends Service {
    private final IBinder binder = new ServiceBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class ServiceBinder extends Binder {
        FlickrService getService() {
            return FlickrService.this;
        }
    }

    public List<Pictures> getListPicture() {
        List<Pictures> listPicture = new ArrayList<>();

        String url = "http://img0.mxstatic.com/wallpapers/b962ef609d737c4668f7b3e433bf615f_large.jpeg";
        listPicture.add(new Pictures("Chemin sous la neige",url));

        url = "http://img0.mxstatic.com/wallpapers/02101268868990cddebf65f4f5727c54_large.jpeg";
        listPicture.add(new Pictures("riviere sous la neige",url));

        url = "http://img0.mxstatic.com/wallpapers/ee16dbe59b10dae450472b8b3d40a305_large.jpeg";
        listPicture.add(new Pictures("maison sous la neige",url));
        return listPicture;
    }
}
