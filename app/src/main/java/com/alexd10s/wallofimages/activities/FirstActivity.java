package com.alexd10s.wallofimages.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alexd10s.loadinglibrary.LoadingLibrary;
import com.alexd10s.wallofimages.R;

public class FirstActivity extends AppCompatActivity {

    private Button picassoButton;
    private Button mylibraryButton;
    private String jsonData;

    private final String URL_DATA = "http://pastebin.com/raw/wgkJgazE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new getData().execute("");
        //getData();
        //setupButtons();

    }

    private class getData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            final String jsonToReturn = LoadingLibrary.get(getBaseContext()).load(URL_DATA).into();

            return jsonToReturn;
        }

        @Override
        protected void onPostExecute(String result) {
            jsonData = result;

            setContentView(R.layout.activity_first);
            setupButtons();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }



    private void setupButtons(){
        picassoButton = (Button) findViewById(R.id.Picasso_button);
        picassoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickPicasso();
            }
        });

        mylibraryButton = (Button) findViewById(R.id.MyLibrary_button);
        mylibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickMyLibrary();
            }
        });
    }

    private void pickPicasso(){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("dataInJson", jsonData);
        intent.putExtra("library", "Picasso");
        startActivity(intent);
    }

    private void pickMyLibrary(){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("dataInJson", jsonData);
        intent.putExtra("library", "MyLibrary");
        startActivity(intent);
    }
}
