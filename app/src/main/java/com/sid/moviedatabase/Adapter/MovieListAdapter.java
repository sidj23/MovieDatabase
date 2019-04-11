package com.sid.moviedatabase.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sid.moviedatabase.Model.RecyclerMovieListModel;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.UI.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.sid.moviedatabase.Constants.IMG_URL;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    Context context;
    List<RecyclerMovieListModel> movieList;

    public MovieListAdapter(Context context, List<RecyclerMovieListModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public void appendMovies(List<RecyclerMovieListModel> moviesToAppend) {
        movieList.addAll(moviesToAppend);
        notifyDataSetChanged();
    }

    public void sortedList(List<RecyclerMovieListModel> moviesToAppend,String sortingType){
        movieList.addAll(moviesToAppend);
        collectionsSorting(sortingType);
        notifyDataSetChanged();
    }

    public void collectionsSorting(String sortingType){
        if(sortingType.equals("name")){
            Collections.sort(movieList, new Comparator<RecyclerMovieListModel>(){
                @Override
                public int compare(RecyclerMovieListModel o1, RecyclerMovieListModel o2) {

                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            });
        }
        else if(sortingType.equals("rating")){
            Collections.sort(movieList, new Comparator<RecyclerMovieListModel>(){
                @Override
                public int compare(RecyclerMovieListModel o1, RecyclerMovieListModel o2) {

                    return o2.getRating().compareToIgnoreCase(o1.getRating());
                }
            });
        }
        else if(sortingType.equals("date_asc")){
            Collections.sort(movieList, new Comparator<RecyclerMovieListModel>(){
                @Override
                public int compare(RecyclerMovieListModel o1, RecyclerMovieListModel o2) {

                    return o1.getDate().compareToIgnoreCase(o2.getDate());
                }
            });
        }

        else if(sortingType.equals("date_desc")){
            Collections.sort(movieList, new Comparator<RecyclerMovieListModel>(){
                @Override
                public int compare(RecyclerMovieListModel o1, RecyclerMovieListModel o2) {

                    return o2.getDate().compareToIgnoreCase(o1.getDate());
                }
            });
        }
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder{

        ImageView imgProf;
        TextView tvName,tvRating,tvVotes,tvDate;
        RelativeLayout relativeLayout;

        public MovieListViewHolder(@NonNull View view) {
            super(view);

            imgProf=view.findViewById(R.id.img_ml_prof);
            tvName=view.findViewById(R.id.tv_ml_title);
            tvRating=view.findViewById(R.id.tv_ml_rating);
            tvVotes=view.findViewById(R.id.tv_ml_votes);
            tvDate=view.findViewById(R.id.tv_ml_date);
            relativeLayout=view.findViewById(R.id.relay_movie_list);
        }
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieListViewHolder(LayoutInflater.from(context).inflate(R.layout.list_movies,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int i) {

        final RecyclerMovieListModel movie = movieList.get(i);

        holder.tvName.setText(movie.getName());
        holder.tvRating.setText(movie.getRating()+"/10");
        holder.tvVotes.setText("("+movie.getVote()+")");
        holder.tvDate.setText("Release Date: "+movie.getDate());

        Picasso.get()
                .load(IMG_URL+movie.getImgUrl())
                .into(holder.imgProf);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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
