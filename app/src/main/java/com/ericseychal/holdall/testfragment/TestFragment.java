package com.ericseychal.holdall.testfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ericseychal.holdall.R;

/**
 * Created by ericseychal on 23/11/2016.
 */

public class TestFragment extends Fragment {
    final static String TESTFRAGMENT_NAME = "name";
    final static String TESTFRAGMENT_INDICE = "indice";
    private Bundle args;
    private TextView textView;
    private Button button;
    private Button buttonAdd;
    private int indice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.test_fragment_textview);
        button = (Button) view.findViewById(R.id.test_fragment_button);
        buttonAdd = (Button) view.findViewById(R.id.button_add);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        args = getArguments();
        indice = getIndice();
        showIndice(indice);
        button.setText(getName());
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indice ++;
                showIndice(indice);
            }
        });

    }

    public static TestFragment newInstance(int indice, String name) {
        TestFragment testFrag = new TestFragment();
        Bundle args = new Bundle();
        args.putInt(TESTFRAGMENT_INDICE, indice);
        args.putString(TESTFRAGMENT_NAME, name);
        testFrag.setArguments(args);
        return testFrag;
    }

    private String getName() {
        String name = "";
        if (args != null) {
            name = args.getString(TESTFRAGMENT_NAME);
        }
        return name;
    }

    private int getIndice() {
        int indice = -1;
        if (args != null) {
            indice = args.getInt(TESTFRAGMENT_INDICE);
        }
        return indice;
    }
    private void showIndice(int indice) {
        textView.setText(String.valueOf(indice));
    }
}
