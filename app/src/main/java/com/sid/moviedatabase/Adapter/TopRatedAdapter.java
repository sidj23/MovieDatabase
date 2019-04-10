package com.sid.moviedatabase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sid.moviedatabase.Model.RecyclerTopRatedModel;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.RetrofitModel.TopRatedModel;
import com.sid.moviedatabase.UI.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.sid.moviedatabase.Constants.IMG_URL;

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.TopRatedViewHolder> {

    Context context;
    List<RecyclerTopRatedModel> movieList;

    public TopRatedAdapter(Context context, List<RecyclerTopRatedModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public class TopRatedViewHolder extends RecyclerView.ViewHolder{

        TextView name,rating;
        ImageView poster;
        LinearLayout linearLayout;

        public TopRatedViewHolder(@NonNull View view) {
            super(view);

            name=view.findViewById(R.id.tv_tr_movie_name);
            rating=view.findViewById(R.id.tv_tr_rating);
            poster=view.findViewById(R.id.img_tr_poster);
            linearLayout=view.findViewById(R.id.linear_top_rated);
        }
    }

    @NonNull
    @Override
    public TopRatedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopRatedViewHolder(LayoutInflater.from(context).inflate(R.layout.list_top_rated,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedViewHolder holder, int i) {

        final RecyclerTopRatedModel movie = movieList.get(i);

        holder.name.setText(movie.getName());

        holder.rating.setText(movie.getRating()+"/10");

        Picasso.get()
                .load(IMG_URL+movie.getImgUrl())
                .into(holder.poster);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent putIntent = new Intent(context, MovieDetailsActivity.class);
                putIntent.putExtra("movieId",movie.getId());
                context.startActivity(putIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
