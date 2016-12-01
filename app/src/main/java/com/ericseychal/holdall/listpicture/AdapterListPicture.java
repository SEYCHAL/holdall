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
import com.ericseychal.holdall.dbflow.PicturesManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ericseychal on 22/11/2016.
 */

public class AdapterListPicture extends BaseAdapter {
    private Context context;
    private List<Pictures> picturesList = new ArrayList<>();
    private boolean isHistoric = false;

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
    public Pictures getItem(int position) {
        return picturesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean isHistoric() {
        return isHistoric;
    }

    public void setHistoric(boolean historic) {
        isHistoric = historic;
    }

    private void pictureDel(int position) {
        if (isHistoric) {
            PicturesManager picturesManager = new PicturesManager(context);
            picturesManager.delete(picturesList.get(position));
        }
        picturesList.remove(position);
        setPicturesList(picturesList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_picture_row,parent,false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.list_picture_title);
        TextView url = (TextView) convertView.findViewById(R.id.list_picture_url);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.list_picture_picture_1);
        title.setText(picturesList.get(position).getName());
        url.setText(picturesList.get(position).getUrl());
        Picasso.with(context).load(picturesList.get(position).getUrl()).resize(200,200).into(imageView);

        FloatingActionButton fab = (FloatingActionButton) convertView.findViewById(R.id.FAB);
        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictureDel(position);
            }
        });

        return convertView;
    }
}
