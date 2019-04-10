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
import android.widget.Switch;
import android.widget.TextView;

import com.sid.moviedatabase.Model.RecyclerComingSoonModel;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.RetrofitModel.ComingSoonModel;
import com.sid.moviedatabase.UI.MovieDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sid.moviedatabase.Constants.IMG_URL;

public class ComingSoonAdapter extends RecyclerView.Adapter<ComingSoonAdapter.ComingSoonViewHolder> {

    Context context;
    List<RecyclerComingSoonModel> movieList;

    public ComingSoonAdapter(Context context, List<RecyclerComingSoonModel> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    public class ComingSoonViewHolder extends RecyclerView.ViewHolder{

        TextView name,releaseDate;
        ImageView poster;
        LinearLayout linearLayout;

        public ComingSoonViewHolder(@NonNull View view) {
            super(view);

            name=view.findViewById(R.id.tv_cs_movie_name);
            releaseDate=view.findViewById(R.id.tv_cs_rel_date);
            poster=view.findViewById(R.id.img_cs_poster);
            linearLayout=view.findViewById(R.id.linear_coming_soon);
        }
    }

    @NonNull
    @Override
    public ComingSoonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ComingSoonViewHolder(LayoutInflater.from(context).inflate(R.layout.list_coming_soon,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonViewHolder holder, int i) {

        final RecyclerComingSoonModel movie = movieList.get(i);

        Picasso.get()
                .load(IMG_URL+movie.getImgUrl())
                .into(holder.poster);

        holder.name.setText(movie.getName());

        String num = movie.getReleaseDate();
        String str[] = num.split("-");
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);
        int month = Integer.parseInt(al.get(1));

        switch (month){
            case 1:
                holder.releaseDate.setText("Jan "+al.get(2));
                break;
            case 2:
                holder.releaseDate.setText("Feb "+al.get(2));
                break;
            case 3:
                holder.releaseDate.setText("Mar "+al.get(2));
                break;
            case 4:
                holder.releaseDate.setText("Apr "+al.get(2));
                break;
            case 5:
                holder.releaseDate.setText("May "+al.get(2));
                break;
            case 6:
                holder.releaseDate.setText("Jun "+al.get(2));
                break;
            case 7:
                holder.releaseDate.setText("Jul "+al.get(2));
                break;
            case 8:
                holder.releaseDate.setText("Aug "+al.get(2));
                break;
            case 9:
                holder.releaseDate.setText("Sep "+al.get(2));
                break;
            case 10:
                holder.releaseDate.setText("Oct "+al.get(2));
                break;
            case 11:
                holder.releaseDate.setText("Nov "+al.get(2));
                break;
            case 12:
                holder.releaseDate.setText("Dec "+al.get(2));
                break;
        }

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
