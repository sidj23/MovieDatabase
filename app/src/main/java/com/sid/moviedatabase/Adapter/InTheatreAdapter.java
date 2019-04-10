package com.sid.moviedatabase.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sid.moviedatabase.Model.RecyclerInTheatreModel;
import com.sid.moviedatabase.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import static com.sid.moviedatabase.Constants.IMG_URL;

public class InTheatreAdapter extends RecyclerView.Adapter<InTheatreAdapter.InTheaterViewHolder> {

    Context context;
    List<RecyclerInTheatreModel> movieList;

    public InTheatreAdapter(Context context, List<RecyclerInTheatreModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public class InTheaterViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPoster;
        TextView name,rating;

        public InTheaterViewHolder(@NonNull View view) {
            super(view);
            imgPoster=view.findViewById(R.id.img_it_poster);
            name=view.findViewById(R.id.tv_it_movie_name);
            rating=view.findViewById(R.id.tv_it_rating);
        }


    }

    @NonNull
    @Override
    public InTheaterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new InTheaterViewHolder(LayoutInflater.from(context).inflate(R.layout.list_in_theatre,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InTheaterViewHolder holder, int i) {

        RecyclerInTheatreModel movie = movieList.get(i);

        Picasso.get()
                .load(IMG_URL+movie.getImgUrl())
                .into(holder.imgPoster);

        holder.name.setText(movie.getName());

        holder.rating.setText(movie.getRating()+"/10");
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
