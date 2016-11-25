package com.ericseychal.holdall;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_picture);

        final List<Pictures> listPicture = new ArrayList<>();

        String url = "http://img0.mxstatic.com/wallpapers/b962ef609d737c4668f7b3e433bf615f_large.jpeg";
        listPicture.add(new Pictures("Chemin sous la neige",url));

        url = "http://img0.mxstatic.com/wallpapers/02101268868990cddebf65f4f5727c54_large.jpeg";
        listPicture.add(new Pictures("riviere sous la neige",url));

        url = "http://img0.mxstatic.com/wallpapers/ee16dbe59b10dae450472b8b3d40a305_large.jpeg";
        listPicture.add(new Pictures("maison sous la neige",url));

        AdapterListPicture adapterListPicture = new AdapterListPicture(this);
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
}
