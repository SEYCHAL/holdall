package com.ericseychal.holdall.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ericseychal.holdall.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        List<String> dogsName;
        ListView listView;

        dogsName = new ArrayList<>();
        dogsName.add("Rantanplan");
        dogsName.add("Milou");
        dogsName.add("Rex");
        dogsName.add("Medor");
        dogsName.add("Snopy");

        AdapterListView adapterListView = new AdapterListView(this);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapterListView);
        adapterListView.setDogsList(dogsName);



    }
}
