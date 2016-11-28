package com.ericseychal.holdall.listpicture;

import java.util.List;

/**
 * Created by ericseychal on 28/11/2016.
 */

public interface FlickrResponseListener {
    void onPicturesReceived(List<Pictures> picturesList);
}
