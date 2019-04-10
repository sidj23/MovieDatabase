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

import com.sid.moviedatabase.Model.RecyclerPopularModel;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.UI.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.sid.moviedatabase.Constants.IMG_URL;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder> {

    List<RecyclerPopularModel> moviesList ;
    Context context;

    public MostPopularAdapter(Context context,List<RecyclerPopularModel> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MostPopularViewHolder extends RecyclerView.ViewHolder{

        TextView tvNames,tvRank;
        ImageView imgPoster;
        LinearLayout linearLayout;


        public MostPopularViewHolder(@NonNull View view) {
            super(view);
            tvNames= view.findViewById(R.id.tv_movie_name);
            tvRank=view.findViewById(R.id.tv_rank);
            imgPoster=view.findViewById(R.id.img_poster);
            linearLayout=view.findViewById(R.id.linear_most_popular);
        }
    }

    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MostPopularViewHolder(LayoutInflater.from(context).inflate(R.layout.list_most_popular,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder holder, int i) {
        final RecyclerPopularModel movies = moviesList.get(i);

        holder.tvNames.setText(movies.getTitle());

        holder.tvRank.setText(movies.getRank());

        Picasso.get()
                .load(IMG_URL+movies.getImgUrl())
                .into(holder.imgPoster);



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent putIntent = new Intent(context, MovieDetailsActivity.class);
                putIntent.putExtra("movieId",movies.getMovieId());
                context.startActivity(putIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
