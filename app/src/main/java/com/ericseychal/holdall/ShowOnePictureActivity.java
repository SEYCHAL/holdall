package com.ericseychal.holdall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ShowOnePictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_one_picture);
        Pictures picture = (Pictures) getIntent().getSerializableExtra("picture");

        TextView title = (TextView) findViewById(R.id.one_picture_title);
        TextView url = (TextView) findViewById(R.id.one_picture_url);
        ImageView imageView = (ImageView) findViewById(R.id.one_picture_image);

        title.setText(picture.getName());
        url.setText(picture.getUrl());
        Picasso.with(this).load(picture.getUrl()).into(imageView);

        Button deleteButton = (Button) findViewById(R.id.one_picture_delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
