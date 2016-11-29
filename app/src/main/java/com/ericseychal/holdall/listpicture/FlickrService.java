package com.ericseychal.holdall.listpicture;

import android.app.Service;
import android.content.Intent;
import android.graphics.Picture;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ericseychal.holdall.R;
import com.ericseychal.holdall.flickrdto.ConvertFlickrDto;
import com.ericseychal.holdall.flickrdto.FlickrResponseDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ericseychal on 25/11/2016.
 */

public class FlickrService extends Service {

    final String URL = "https://www.flickr.com/";
    private final IBinder binder = new ServiceBinder();
    private FlickrServiceInterface service;
    private FlickrResponseListener flickrResponseListener;

    public void setFlickrResponseListener(FlickrResponseListener flickrResponseListener) {
        this.flickrResponseListener = flickrResponseListener;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(FlickrServiceInterface.class);
        return binder;
    }

    public class ServiceBinder extends Binder {
        FlickrService getService() {
            return FlickrService.this;
        }
    }

    public void getListPicture(String query, String nber) {


        Call<FlickrResponseDto> flickrPhotosResponseCall = service.getPhotos(query, getFlickrKey(),nber);

        flickrPhotosResponseCall.enqueue(new Callback<FlickrResponseDto>() {
            @Override
            public void onResponse(Call<FlickrResponseDto> call, Response<FlickrResponseDto> response) {
                List<Pictures> listPicture = ConvertFlickrDto.convert(response.body());
                flickrResponseListener.onPicturesReceived(listPicture);
            }

            @Override
            public void onFailure(Call<FlickrResponseDto> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }

    private String getFlickrKey() {
        return getResources().getString(R.string.flickr_api_key);
    }

}
