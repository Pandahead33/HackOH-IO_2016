package com.example.loganpatino.hackohio2016;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VotingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
    }

    private class getLocationOptions extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            Gson gson = new Gson();
            HttpURLConnection urlConnection = null;
            StringBuilder sb = new StringBuilder();

            try {
                URL url = new URL("test");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            catch (Exception e) {
                Log.e("CONNECTION ERROR", "HTTP connection error");
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            //mPreferredUsersList = gson.fromJson(sb.toString(), OtherUser[].class);
            return null;
        }
    }
}
