package com.sid.moviedatabase.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sid.moviedatabase.Model.RecyclerPopular;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;

import java.util.ArrayList;
import java.util.List;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder> {

    List<RecyclerPopular> moviesList ;
    Context context;

    public MostPopularAdapter(Context context,List<RecyclerPopular> moviesList) {
        this.moviesList = moviesList;
        this.context = context;
    }

    public class MostPopularViewHolder extends RecyclerView.ViewHolder{

        TextView tvNames;


        public MostPopularViewHolder(@NonNull View view) {
            super(view);
            tvNames= view.findViewById(R.id.tv_movie_name);
        }
    }

    @NonNull
    @Override
    public MostPopularViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MostPopularViewHolder(LayoutInflater.from(context).inflate(R.layout.list_most_popular,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MostPopularViewHolder holder, int i) {
        RecyclerPopular movies = moviesList.get(i);

        holder.tvNames.setText(movies.getTitle());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
