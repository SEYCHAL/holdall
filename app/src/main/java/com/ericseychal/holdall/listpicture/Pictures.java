package com.ericseychal.holdall.listpicture;

import android.support.annotation.ColorInt;

import com.ericseychal.holdall.dbflow.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;

/**
 * Created by ericseychal on 22/11/2016.
 */

@Table(database = AppDatabase.class)
public class Pictures extends BaseModel implements Serializable{
    @Column
    @PrimaryKey(autoincrement = true)
    private  long id;

    @Column
    private String name;
    @Column
    private String url;

    public Pictures(){

    }
    public Pictures(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
