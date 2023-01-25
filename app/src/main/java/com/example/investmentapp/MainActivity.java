package com.example.investmentapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String apiKey = "KN4OJJ69TA7SFREQ";
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = this.getWindow().getDecorView();
        v.setSystemUiVisibility(View.INVISIBLE);
    }

    public void LaunchSearch(View v) {
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

    public void tickerSearch(View v){
        EditText searchText = findViewById(R.id.searchText);
        String ticker = searchText.getText().toString().toUpperCase();
        new TickerSearchTask().execute(ticker);
    }

    private class TickerSearchTask extends AsyncTask<String, Void, String> {

        private ProgressDialog progress;
        String quoteName;
        String quotePrice;

        @Override
        protected void onPreExecute() {
            progress = new ProgressDialog(MainActivity.this);
            progress.setTitle("Loading");
            progress.setMessage("Please wait ...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String ticker = params[0];
            String apiPrice = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + URLEncoder.encode(ticker) + "&apikey=" + apiKey;
            String apiOverview = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=" + URLEncoder.encode(ticker) + "&apikey=" + apiKey;

            try {
                URL url1 = new URL(apiPrice);
                URL url2 = new URL(apiOverview);
                HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                connection1.setRequestMethod("GET");
                connection1.setConnectTimeout(5000);
                connection1.setReadTimeout(5000);
                connection2.setRequestMethod("GET");
                connection2.setConnectTimeout(5000);
                connection2.setReadTimeout(5000);

                int responseCode1 = connection1.getResponseCode();
                if (responseCode1 != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + connection1.getResponseCode());
                }

                int responseCode2 = connection2.getResponseCode();
                if (responseCode2 != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + connection2.getResponseCode());
                }

                BufferedReader br1 = new BufferedReader(new InputStreamReader((connection1.getInputStream())));
                BufferedReader br2 = new BufferedReader(new InputStreamReader((connection2.getInputStream())));

                StringBuilder stringBuilder1 = new StringBuilder();
                String output1;

                StringBuilder stringBuilder2 = new StringBuilder();
                String output2;

                while ((output1 = br1.readLine()) != null) {
                    stringBuilder1.append(output1);
                }

                while ((output2 = br2.readLine()) != null) {
                    stringBuilder2.append(output2);
                }

                JSONObject json1 = new JSONObject(stringBuilder1.toString());
                JSONObject quote1 = json1.getJSONObject("Global Quote");
                String quotePrice1 = quote1.getString("05. price");

                JSONObject json2 = new JSONObject(stringBuilder2.toString());
                String quoteName1 = json2.getString("Name");

                quoteName = quoteName1;
                quotePrice = quotePrice1;

                return "";

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            progress.dismiss();

            if (result != null) {
                TextView stockpriceText = findViewById(R.id.stockpriceText);
                stockpriceText.setText("Stock Price: "+quotePrice);
                stockpriceText.setVisibility(View.VISIBLE);

                TextView stocknameText = findViewById(R.id.stocknameText);
                stocknameText.setText("Stock Name: "+quoteName);
                stocknameText.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(MainActivity.this, "An error occurred while retrieving the stock price.", Toast.LENGTH_SHORT).show();
            }
        }
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





