package com.example.investmentapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.investmentapp.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = this.getWindow().getDecorView();
        v.setSystemUiVisibility(View.INVISIBLE);

    }
    public void LaunchSearch(View v){
        Intent i = new Intent(this, search_screen.class);
        setContentView(R.layout.search_screen);
    }

    public void backtoMain(View v) {
        Intent i = new Intent(this, MainActivity.class);
        setContentView(R.layout.activity_main);
    }

    public void toLogin(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        setContentView(R.layout.activity_login);
    }
}