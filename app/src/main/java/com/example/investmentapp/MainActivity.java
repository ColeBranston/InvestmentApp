package com.example.investmentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Intent i = new Intent(this, settings.class);
        setContentView(R.layout.activity_settings);
    }

    public void login(View v) {

        EditText usernameInput = findViewById(R.id.username);
        EditText passwordInput = findViewById(R.id.password);

        String user = usernameInput.getText().toString();
        String pass = passwordInput.getText().toString();

        boolean back = false;
        String text = "";

        // to get Context
        Context context = getApplicationContext();

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);

        if (user.equals("") || pass.equals("")){
            // message to display
            text = "Missing Information";

            usernameInput.setBackgroundColor(Color.argb(20, 255, 0, 0));
            passwordInput.setBackgroundColor(Color.argb(20, 255, 0, 0));

        }

        else {
            // message to display
            text = "Welcome " + user;
            back = true;

        }

        // toast time duration, can also set manual value
        int duration = Toast.LENGTH_LONG;
        toast = Toast.makeText(context, text, duration);

        // to show the toast
        toast.show();

        if (back){backtoMain(null);}


        }

    public void registerNow(View v){
        Intent i = new Intent(this, register.class);
        setContentView(R.layout.activity_register);
    }

}