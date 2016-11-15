package com.ericseychal.holdall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        String urlPicture = "http://img0.mxstatic.com/wallpapers/b962ef609d737c4668f7b3e433bf615f_large.jpeg";

        ImageView imageView = (ImageView) findViewById(R.id.picture_1);
        Picasso.with(this).load(urlPicture).into(imageView);
    }
}
