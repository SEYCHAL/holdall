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

    @Override
    public String toString() {
        return "Pictures{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pictures)) return false;

        Pictures pictures = (Pictures) o;

        if (name != null ? !name.equals(pictures.name) : pictures.name != null) return false;
        return url != null ? url.equals(pictures.url) : pictures.url == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
