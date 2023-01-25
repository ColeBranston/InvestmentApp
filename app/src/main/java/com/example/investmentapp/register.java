package com.example.investmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //EditText email = (EditText) findViewById(R.id.)
    }

    public void toLogin(View v) {
        Intent i = new Intent(this, settings.class);
        setContentView(R.layout.activity_settings);
    }


}