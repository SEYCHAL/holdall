package com.ericseychal.holdall.fibonnaci;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ericseychal.holdall.R;

public class FibonnaciActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonnaci);

        FibonnaciManager fibonnaciManager = new FibonnaciManager();
        TextView textView = (TextView) findViewById(R.id.fibonnaci_textview);
        textView.setText(fibonnaciManager.fibonnaciSuite(5000));
    }
}
