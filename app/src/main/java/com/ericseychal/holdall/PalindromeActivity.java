package com.ericseychal.holdall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PalindromeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palindrome);

        final EditText editText = (EditText) findViewById(R.id.edittext_1);
        final TextView textView = (TextView) findViewById(R.id.palindrom_textview_1);

        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word =editText.getText().toString();
                Toast.makeText(PalindromeActivity.this,editText.getText(),Toast.LENGTH_SHORT).show();
                Palindrome palindrome = new Palindrome();
                textView.setText(palindrome.toward(word));
            }
        });
    }
}
