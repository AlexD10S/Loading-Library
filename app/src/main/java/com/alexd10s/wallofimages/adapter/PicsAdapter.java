package com.alexd10s.wallofimages.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexd10s.loadinglibrary.LoadingLibrary;
import com.alexd10s.wallofimages.R;
import com.alexd10s.wallofimages.model.Pin;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by alex on 22/09/2018.
 */

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.ViewHolder> {

    private ArrayList<Pin> dataset;
    private String library;
    private LayoutInflater inflater;
    Context context;

    public PicsAdapter(Context context, ArrayList<Pin> dataset, String library) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.dataset = dataset;
        this.library = library;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private TextView likesText;
        private LikeButton likeButton;

        public ViewHolder(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.image_list);
            likesText = (TextView) v.findViewById(R.id.likes_text);
            likeButton = (LikeButton) v.findViewById(R.id.star_button);
        }
    }

    @Override
    public PicsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.pic_layout, parent, false);

        PicsAdapter.ViewHolder holder = new PicsAdapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String element = dataset.get(position).getUrls().getSmall();

        if(library.equals("Picasso")) {
            Picasso.get().
                    load(element)
                    .fit()
                    .into(holder.imageView);
        }
        else if(library.equals("MyLibrary")){
            LoadingLibrary.get(context)
                    .load(element)
                    .into(holder.imageView);
        }

        holder.likesText.setText("Likes: "+dataset.get(position).getLikes());

        if(dataset.get(position).isLiked_by_user()){
            holder.likeButton.setLiked(true);
        }
        else{
            holder.likeButton.setLiked(false);
        }

        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                int newLikes= (dataset.get(position).getLikes() + 1);
                holder.likesText.setText("Likes: "+ newLikes);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //int newLikes= (dataset.get(position).getLikes() - 1);
                holder.likesText.setText("Likes: "+dataset.get(position).getLikes());
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
