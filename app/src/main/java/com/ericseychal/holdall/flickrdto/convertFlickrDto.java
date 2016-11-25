package com.ericseychal.holdall.flickrdto;

import com.ericseychal.holdall.Pictures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericseychal on 25/11/2016.
 */

public class convertFlickrDto {

    public static List<Pictures> convert(FlickrResponseDto flickrResponseDto) {
        FlickrPhotosDto flickrPhotosDto = flickrResponseDto.getPhotos();
        List<PhotoDto> photoDto = new ArrayList<>();
        List <Pictures> convert = new ArrayList<>();
        photoDto = flickrPhotosDto.getPhoto();
        for (PhotoDto photo : photoDto) {
            convert.add( new Pictures(photo.getTitle(),treatment(photo)));
        }
        return convert;
    }

    private static String treatment(PhotoDto photoDto) {
        String variableReturn ="https://farm";
        variableReturn += photoDto.getFarm();
        variableReturn += ".static.flickr.com/";
        variableReturn += photoDto.getServer();
        variableReturn += "/" + photoDto.getId()+"_"+photoDto.getSecret();
        variableReturn += ".jpg";
        return variableReturn;
    }
}
