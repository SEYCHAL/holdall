package com.ericseychal.holdall.listpicture;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericseychal.holdall.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ericseychal on 22/11/2016.
 */

public class AdapterListPicture extends BaseAdapter {
    private Context context;
    List<Pictures> picturesList = new ArrayList<>();

    @BindView (R.id.list_picture_title) TextView title;
    @BindView (R.id.list_picture_url) TextView url;
    @BindView (R.id.list_picture_picture_1) ImageView imageView;
    @BindView (R.id.FAB) FloatingActionButton fab;

    public AdapterListPicture(Context context) {
        this.context = context;
    }

    public void setPicturesList(List<Pictures> picturesList) {
        this.picturesList = picturesList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return picturesList.size();
    }

    @Override
    public Object getItem(int position) {
        return picturesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void pictureDel(int position) {
        picturesList.remove(position);
        setPicturesList(picturesList);
    }

    @Override
    @OnClick(R.id.FAB)
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_picture_row,parent,false);
        }

        title.setText(picturesList.get(position).getName());
        url.setText(picturesList.get(position).getUrl());
        Picasso.with(context).load(picturesList.get(position).getUrl()).resize(200,200).into(imageView);

        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);



//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pictureDel(position);
//            }
//        });

        return convertView;
    }
}
