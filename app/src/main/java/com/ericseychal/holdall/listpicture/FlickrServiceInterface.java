package com.ericseychal.holdall.listpicture;

import com.ericseychal.holdall.flickrdto.FlickrResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ericseychal on 28/11/2016.
 */

public interface FlickrServiceInterface {
    @GET("services/rest/?method=flickr.photos.search&safe_search=1&per_page=5&format=json&nojsoncallback=1")
    Call<FlickrResponseDto> getPhotos(@Query("tags") String query, @Query("api_key") String api_key,@Query("per_page") String nber);
}
