package com.alexd10s.wallofimages.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexd10s.loadinglibrary.LoadingLibrary;
import com.alexd10s.wallofimages.R;
import com.alexd10s.wallofimages.model.Pin;
import com.alexd10s.wallofimages.model.User;
import com.alexd10s.wallofimages.parser.ModelParser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    ImageView imageProfile;
    TextView nameProfile;
    TextView usernameProfile;

    private String json;
    private String library = "Picasso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getIntents();
        fillUserData();
    }

    private void getIntents() {
        if (getIntent().getStringExtra("dataInJson") != null) {
            json = getIntent().getStringExtra("dataInJson");
        }
        if (getIntent().getStringExtra("library") != null) {
            library = getIntent().getStringExtra("library");
        }
    }

    private void fillUserData(){
        List<Pin> listOfPicsAux = ModelParser.getPins(json);

        imageProfile = (ImageView) findViewById(R.id.profile_image);
        nameProfile = (TextView) findViewById(R.id.profile_name);
        usernameProfile = (TextView) findViewById(R.id.profile_username);

        User user = listOfPicsAux.get(0).getUser();

        if(library.equals("Picasso")) {
            Picasso.get().
                    load(user.getProfile_image().getSmall())
                    .fit()
                    .into(imageProfile);
        }
        else if(library.equals("MyLibrary")){
            LoadingLibrary.get(this).
                    load(user.getProfile_image().getSmall()).
                    into(imageProfile);
        }
        nameProfile.setText(user.getName());
        usernameProfile.setText(user.getUsername());

    }
}
