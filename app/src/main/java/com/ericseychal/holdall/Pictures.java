package com.ericseychal.holdall;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by ericseychal on 22/11/2016.
 */

public class Pictures implements Serializable{
    private String name;
    private String url;

    public Pictures(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
