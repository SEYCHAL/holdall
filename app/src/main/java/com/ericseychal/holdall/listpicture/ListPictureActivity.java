package com.ericseychal.holdall.listpicture;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ericseychal.holdall.R;

import java.util.ArrayList;
import java.util.List;

public class ListPictureActivity extends AppCompatActivity {
    private FlickrService flickrService;
    boolean bound = false;
    List<Pictures> listPicture = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_picture);



        final AdapterListPicture adapterListPicture = new AdapterListPicture(this);
        final EditText editText = (EditText) findViewById(R.id.list_picture_edittext);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.list_picture_fab);
        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);

        ListView listView = (ListView) findViewById(R.id.list_picture);
        listView.setAdapter(adapterListPicture);
        adapterListPicture.setPicturesList(listPicture);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListPictureActivity.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                listPicture = flickrService.getListPicture();
                adapterListPicture.setPicturesList(listPicture);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListPictureActivity.this, ShowOnePictureActivity.class);
                intent.putExtra("picture", listPicture.get(position));
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, FlickrService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (bound) {
            unbindService(mConnection);
            bound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            FlickrService.ServiceBinder binder = (FlickrService.ServiceBinder) service;
            flickrService = binder.getService();
            bound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            bound = false;
        }
    };
}
