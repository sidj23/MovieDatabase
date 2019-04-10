package com.sid.moviedatabase.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.sid.moviedatabase.Adapter.InTheatreAdapter;
import com.sid.moviedatabase.Adapter.MostPopularAdapter;
import com.sid.moviedatabase.Model.RecyclerInTheatreModel;
import com.sid.moviedatabase.Model.RecyclerPopularModel;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.Retrofit.ApiUtils;
import com.sid.moviedatabase.RetrofitModel.InTheatreModel;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.moviedatabase.Constants.API_KEY;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerMostPopular;
    List<RecyclerPopularModel> movieList;
    MostPopularAdapter mostPopularAdapter;

    RecyclerView recyclerInTheater;
    List<RecyclerInTheatreModel> itMovieList;
    InTheatreAdapter inTheatreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        recyclerMostPopular = findViewById(R.id.recycler_most_popular);
        recyclerInTheater=findViewById(R.id.recycler_in_theatre);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);

        recyclerMostPopular.setLayoutManager(layoutManager);

        LinearLayoutManager itLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerInTheater.setLayoutManager(itLayoutManager);

        mostPopular();

        inTheaters();

        comingSoon();


    }



    public void mostPopular(){
        ApiUtils.getServiceClass().popularMovies(API_KEY,"1").enqueue(new Callback<MostPopularModel>() {
            @Override
            public void onResponse(Call<MostPopularModel> call, Response<MostPopularModel> response) {
                if(response.isSuccessful()){
                    movieList =new ArrayList<>();
                    for(int i=0;i<10;i++){
                        movieList.add(i,new RecyclerPopularModel(response.body().getMovieResults().get(i).getTitle(),
                                Integer.toString(i+1),
                                response.body().getMovieResults().get(i).getImgUrl(),
                                response.body().getMovieResults().get(i).getMovieId()));
                    }
                    mostPopularAdapter = new MostPopularAdapter(MainActivity.this,movieList);
                    recyclerMostPopular.setAdapter(mostPopularAdapter);
                    mostPopularAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MostPopularModel> call, Throwable t) {

            }
        });
    }

    public void inTheaters(){

        ApiUtils.getServiceClass().inTheatreMovies(API_KEY,"1").enqueue(new Callback<InTheatreModel>() {
            @Override
            public void onResponse(Call<InTheatreModel> call, Response<InTheatreModel> response) {
                if (response.isSuccessful()){
                    itMovieList = new ArrayList<>();
                    for(int i=0;i<10;i++){
                        itMovieList.add(i,new RecyclerInTheatreModel(response.body().getResults().get(i).getMovieId(),
                                response.body().getResults().get(i).getMovieName(),
                                response.body().getResults().get(i).getRating(),
                                response.body().getResults().get(i).getImgUrl()));
                    }
                    inTheatreAdapter = new InTheatreAdapter(MainActivity.this,itMovieList);
                    recyclerInTheater.setAdapter(inTheatreAdapter);
                    inTheatreAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<InTheatreModel> call, Throwable t) {

            }
        });

    }

    private void comingSoon() {



    }
}
