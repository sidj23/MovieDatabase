package com.sid.moviedatabase.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sid.moviedatabase.Adapter.ComingSoonAdapter;
import com.sid.moviedatabase.Adapter.InTheatreAdapter;
import com.sid.moviedatabase.Adapter.MostPopularAdapter;
import com.sid.moviedatabase.Adapter.TopRatedAdapter;
import com.sid.moviedatabase.Model.RecyclerComingSoonModel;
import com.sid.moviedatabase.Model.RecyclerInTheatreModel;
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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerMostPopular;
    List<RecyclerPopularModel> movieList;
    MostPopularAdapter mostPopularAdapter;

    RecyclerView recyclerInTheater;
    List<RecyclerInTheatreModel> itMovieList;
    InTheatreAdapter inTheatreAdapter;

    RecyclerView recyclerComSoon;
    List<RecyclerComingSoonModel> csMovieList;
    ComingSoonAdapter comingSoonAdapter;

    RecyclerView recyclerTopRate;
    List<RecyclerTopRatedModel> trMovieList;
    TopRatedAdapter topRatedAdapter;

    TextView saPopular,saInTheater,saComSoon,saTopRated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Movies Database");

        recyclerMostPopular = findViewById(R.id.recycler_most_popular);
        recyclerInTheater=findViewById(R.id.recycler_in_theatre);
        recyclerComSoon=findViewById(R.id.recycler_coming_soon);
        recyclerTopRate=findViewById(R.id.recycler_top_rated);
        saPopular=findViewById(R.id.tv_sa_most_pop);
        saInTheater=findViewById(R.id.tv_sa_in_theatre);
        saComSoon=findViewById(R.id.tv_sa_coming_soon);
        saTopRated=findViewById(R.id.tv_sa_top_rated);


        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerMostPopular.setLayoutManager(layoutManager);

        LinearLayoutManager itLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerInTheater.setLayoutManager(itLayoutManager);

        LinearLayoutManager csLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerComSoon.setLayoutManager(csLayoutManager);

        LinearLayoutManager trLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerTopRate.setLayoutManager(trLayoutManager);


        mostPopular();

        inTheaters();

        comingSoon();

        topRated();

        saPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent putIntent = new Intent(MainActivity.this,MovieListActivity.class);
                putIntent.putExtra("type","mostPopular");
                startActivity(putIntent);
            }
        });

        saInTheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent putIntent = new Intent(MainActivity.this,MovieListActivity.class);
                putIntent.putExtra("type","inTheatre");
                startActivity(putIntent);
            }
        });

        saComSoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent putIntent = new Intent(MainActivity.this,MovieListActivity.class);
                putIntent.putExtra("type","comingSoon");
                startActivity(putIntent);
            }
        });

        saTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent putIntent = new Intent(MainActivity.this,MovieListActivity.class);
                putIntent.putExtra("type","topRated");
                startActivity(putIntent);
            }
        });

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

        ApiUtils.getServiceClass().comingSoon(API_KEY,"1").enqueue(new Callback<ComingSoonModel>() {
            @Override
            public void onResponse(Call<ComingSoonModel> call, Response<ComingSoonModel> response) {

                if(response.isSuccessful()){
                    csMovieList = new ArrayList<>();
                    for(int i=0;i<10;i++){
                        csMovieList.add(i,new RecyclerComingSoonModel(response.body().getResults().get(i).getMovieId(),
                                response.body().getResults().get(i).getMovieName(),
                                response.body().getResults().get(i).getReleaseDate(),
                                response.body().getResults().get(i).getImgUrl()));
                    }

                    comingSoonAdapter = new ComingSoonAdapter(MainActivity.this,csMovieList);
                    recyclerComSoon.setAdapter(comingSoonAdapter);
                    comingSoonAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ComingSoonModel> call, Throwable t) {

            }
        });

    }

    public void topRated (){

        ApiUtils.getServiceClass().topRated(API_KEY,"1").enqueue(new Callback<TopRatedModel>() {
            @Override
            public void onResponse(Call<TopRatedModel> call, Response<TopRatedModel> response) {
                if(response.isSuccessful()){
                    trMovieList = new ArrayList<>();
                    for(int i=0;i<10;i++){
                        trMovieList.add(i,new RecyclerTopRatedModel(response.body().getResults().get(i).getMovieId(),
                                response.body().getResults().get(i).getName(),
                                response.body().getResults().get(i).getRating(),
                                response.body().getResults().get(i).getImgUrl()));
                    }
                    topRatedAdapter = new TopRatedAdapter(MainActivity.this,trMovieList);
                    recyclerTopRate.setAdapter(topRatedAdapter);
                    topRatedAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TopRatedModel> call, Throwable t) {

            }
        });
    }
}
