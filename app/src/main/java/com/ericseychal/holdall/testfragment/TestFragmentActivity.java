package com.ericseychal.holdall.testfragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.ericseychal.holdall.R;

public class TestFragmentActivity extends FragmentActivity {
    private TestFragment testFragment;
    private TestFragment2 testFragment2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);

        if (findViewById(R.id.fragment_container) != null) {
            testFragment = TestFragment.newInstance(3, "Coucou");
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, testFragment).commit();
        }
    }

    public void goToFragment2(View view) {
        if (testFragment2 == null) {
            testFragment2 = new TestFragment2();
        }
        showFragment(testFragment2);
    }

    public void goToFragment(View view) {
        showFragment(testFragment);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
    }


}
