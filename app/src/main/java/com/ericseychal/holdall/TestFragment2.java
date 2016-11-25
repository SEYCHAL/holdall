package com.ericseychal.holdall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ericseychal on 24/11/2016.
 */

public class TestFragment2 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment2, container, false);
        TextView textView = (TextView) view.findViewById(R.id.test_fragment2_textview);
        Button button = (Button) view.findViewById(R.id.test_fragment2_button);
        return inflater.inflate(R.layout.test_fragment2, container, false);
    }
}
