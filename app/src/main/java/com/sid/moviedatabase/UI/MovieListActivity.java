package com.sid.moviedatabase.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.sid.moviedatabase.Adapter.MostPopularAdapter;
import com.sid.moviedatabase.Adapter.MovieListAdapter;
import com.sid.moviedatabase.Model.RecyclerMovieListModel;
import com.sid.moviedatabase.Model.RecyclerPopularModel;
import com.sid.moviedatabase.Model.RecyclerTopRatedModel;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.Retrofit.ApiUtils;
import com.sid.moviedatabase.RetrofitModel.ComingSoonModel;
import com.sid.moviedatabase.RetrofitModel.InTheatreModel;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;
import com.sid.moviedatabase.RetrofitModel.TopRatedModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.moviedatabase.Constants.API_KEY;

public class MovieListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieListAdapter movieListAdapter;
    List<RecyclerMovieListModel> movieList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=findViewById(R.id.recycler_movie_list);
        Intent get = getIntent();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MovieListActivity.this, LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        switch (get.getStringExtra("type")){
            case "mostPopular" :
                mostPopular();
                setTitle("Most Popular");
                break;
            case "inTheatre":
                inTheatre();
                setTitle("In Theatre");
                break;
            case "comingSoon":
                comingSoon();
                setTitle("Coming Soon");
                break;
            case "topRated":
                topRated();
                setTitle("Top Rated");
                break;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {
            finish();
            return true;
        }
        // other menu select events may be present here

        return super.onOptionsItemSelected(item);
    }

    public void mostPopular(){
        ApiUtils.getServiceClass().popularMovies(API_KEY,"1").enqueue(new Callback<MostPopularModel>() {
            @Override
            public void onResponse(Call<MostPopularModel> call, Response<MostPopularModel> response) {
                if(response.isSuccessful()){
                    movieList= new ArrayList<>();
                    for(int i=0;i<response.body().getMovieResults().size();i++){
                        movieList.add(i,new RecyclerMovieListModel(response.body().getMovieResults().get(i).getMovieId(),
                                response.body().getMovieResults().get(i).getTitle(),
                                response.body().getMovieResults().get(i).getRating(),
                                response.body().getMovieResults().get(i).getCount(),
                                response.body().getMovieResults().get(i).getDate(),
                                response.body().getMovieResults().get(i).getImgUrl()));
                    }
                    movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                    recyclerView.setAdapter(movieListAdapter);
                    movieListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MostPopularModel> call, Throwable t) {

            }
        });
    }

    private void inTheatre() {
        ApiUtils.getServiceClass().inTheatreMovies(API_KEY,"1").enqueue(new Callback<InTheatreModel>() {
            @Override
            public void onResponse(Call<InTheatreModel> call, Response<InTheatreModel> response) {
                if (response.isSuccessful()){
                    movieList= new ArrayList<>();
                    for(int i=0;i<response.body().getResults().size();i++){
                        movieList.add(i,new RecyclerMovieListModel(response.body().getResults().get(i).getMovieId(),
                                response.body().getResults().get(i).getMovieName(),
                                response.body().getResults().get(i).getRating(),
                                response.body().getResults().get(i).getCount(),
                                response.body().getResults().get(i).getDate(),
                                response.body().getResults().get(i).getImgUrl()));
                    }
                    movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                    recyclerView.setAdapter(movieListAdapter);
                    movieListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<InTheatreModel> call, Throwable t) {

            }
        });
    }
    private void comingSoon() {
        ApiUtils.getServiceClass().comingSoon(API_KEY,"1").enqueue(new Callback<ComingSoonModel>() {
            @Override
            public void onResponse(Call<ComingSoonModel> call, Response<ComingSoonModel> response) {
                if (response.isSuccessful()){
                    movieList= new ArrayList<>();
                    for(int i=0;i<response.body().getResults().size();i++){
                        movieList.add(i,new RecyclerMovieListModel(response.body().getResults().get(i).getMovieId(),
                                response.body().getResults().get(i).getMovieName(),
                                response.body().getResults().get(i).getRating(),
                                response.body().getResults().get(i).getCount(),
                                response.body().getResults().get(i).getReleaseDate(),
                                response.body().getResults().get(i).getImgUrl()));
                    }
                    movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                    recyclerView.setAdapter(movieListAdapter);
                    movieListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ComingSoonModel> call, Throwable t) {

            }
        });
    }

    private void topRated() {
        ApiUtils.getServiceClass().topRated(API_KEY,"1").enqueue(new Callback<TopRatedModel>() {
            @Override
            public void onResponse(Call<TopRatedModel> call, Response<TopRatedModel> response) {
                if (response.isSuccessful()){
                    movieList= new ArrayList<>();
                    for(int i=0;i<response.body().getResults().size();i++){
                        movieList.add(i,new RecyclerMovieListModel(response.body().getResults().get(i).getMovieId(),
                                response.body().getResults().get(i).getName(),
                                response.body().getResults().get(i).getRating(),
                                response.body().getResults().get(i).getCount(),
                                response.body().getResults().get(i).getDate(),
                                response.body().getResults().get(i).getImgUrl()));
                    }
                    movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                    recyclerView.setAdapter(movieListAdapter);
                    movieListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TopRatedModel> call, Throwable t) {

            }
        });
    }

}
