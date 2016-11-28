package com.ericseychal.holdall;

import com.ericseychal.holdall.flickrdto.ConvertFlickrDto;
import com.ericseychal.holdall.flickrdto.FlickrPhotosDto;
import com.ericseychal.holdall.flickrdto.FlickrResponseDto;
import com.ericseychal.holdall.flickrdto.PhotoDto;
import com.ericseychal.holdall.listpicture.Pictures;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by ericseychal on 25/11/2016.
 */

public class FlickrUnitTest {

    @Test
    public void convertFlickrDto_IsCorrect() throws Exception {
        List<PhotoDto> listPhoto = new ArrayList<>();
        listPhoto.add(new PhotoDto("30861297610","145052384@N05","299d2972ed","5634",6,"4339316232",1,0,0));
        listPhoto.add(new PhotoDto("30407771724","145052384@N05","d82cfff779","5833",6,"3488367654",1,0,0));

        FlickrPhotosDto flickrPhotosDto = new FlickrPhotosDto(1,65447,5,"327234",listPhoto);
        FlickrResponseDto flickrResponseDto = new FlickrResponseDto(flickrPhotosDto,"ok");

        List <Pictures> listPictures = new ArrayList<>();
        listPictures.add( new Pictures("4339316232","https://farm6.static.flickr.com/5634/30861297610_299d2972ed.jpg"));
        listPictures.add( new Pictures("3488367654","https://farm6.static.flickr.com/5833/30407771724_d82cfff779.jpg"));

//        ConvertFlickrDto ConvertFlickrDto = new ConvertFlickrDto();
        List<Pictures> result = ConvertFlickrDto.convert(flickrResponseDto);

        assertEquals(result, listPictures);
    }


}
