package com.ericseychal.holdall;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ericseychal on 22/11/2016.
 */

public class AdapterListView extends BaseAdapter {
    private Context context;
    private List<String> dogsList = new ArrayList<>();


    public AdapterListView(Context context) {
        this.context = context;
    }

    public List<String> getDogsList() {
        return dogsList;
    }

    public void setDogsList(List<String> dogsList) {
        this.dogsList = dogsList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return dogsList.size();
    }

    @Override
    public String getItem(int position) {
        return dogsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView= LayoutInflater.from(context).inflate(R.layout.row_listview, parent,false);
        }
        TextView dog = (TextView) convertView.findViewById(R.id.text_listview);
        dog.setText(dogsList.get(position));
        return convertView;
    }
}
