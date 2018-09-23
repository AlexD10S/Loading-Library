package com.alexd10s.wallofimages.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alexd10s.wallofimages.R;
import com.alexd10s.wallofimages.adapter.PicsAdapter;
import com.alexd10s.wallofimages.interfaces.IItemClickListener;
import com.alexd10s.wallofimages.model.Pin;
import com.alexd10s.wallofimages.parser.ModelParser;
import com.alexd10s.wallofimages.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IItemClickListener {

    private RecyclerView picsRecyclerView;
    private PicsAdapter picsAdapter;
    private LinearLayoutManager layoutManager;


    ArrayList<Pin> listOfPics = new ArrayList<Pin>();
    private String json = "";
    private String library = "Picasso";

    ProgressDialog dialog;
    private ImageView userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupProgressDialog();
        getIntents();
        setupData();

        dialog.show();
        setupList();
        dialog.cancel();
    }

    private void getIntents(){
        if (getIntent().getStringExtra("dataInJson") != null) {
            json = getIntent().getStringExtra("dataInJson");
        }
        if (getIntent().getStringExtra("library") != null) {
            library = getIntent().getStringExtra("library");
        }
    }

    private void setupProgressDialog() {
        dialog = new ProgressDialog(this); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Downloading images. It could take few seconds Please wait...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
    }

    private void setupData(){
        List<Pin> listOfPicsAux = ModelParser.getPins(json);
        listOfPics.addAll(listOfPicsAux);

        userProfile = (ImageView) findViewById(R.id.user_profile);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeUserProfile();
            }
        });
    }

    private void seeUserProfile(){
        Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
        intent.putExtra("dataInJson", json);
        intent.putExtra("library", "Picasso");
        startActivity(intent);
    }

    private void setupList(){
        picsRecyclerView =  (RecyclerView) findViewById(R.id.pics_recycler_view);
        //String memberToken = getActivity().getIntent().getStringExtra("MemberToken");

        layoutManager = new LinearLayoutManager(this);
        //mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        picsRecyclerView.setLayoutManager(layoutManager);

        picsAdapter = new PicsAdapter(this, listOfPics, library);
        //mDetailsAdapter.notifyDataSetChanged();

        picsRecyclerView.setAdapter(picsAdapter);

        picsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, picsRecyclerView, this));
    }



    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
