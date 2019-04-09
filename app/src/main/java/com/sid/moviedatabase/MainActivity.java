package com.sid.moviedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.sid.moviedatabase.Adapter.MostPopularAdapter;
import com.sid.moviedatabase.Model.RecyclerPopular;
import com.sid.moviedatabase.Retrofit.ApiUtils;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.moviedatabase.Constants.API_KEY;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerMostPopular;
    List<RecyclerPopular> movieList;
    MostPopularAdapter mostPopularAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        recyclerMostPopular = findViewById(R.id.recycler_most_popular);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,true);
        //layoutManager.setReverseLayout(true);

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerMostPopular.setLayoutManager(layoutManager);


        mostPopular();


    }

    public void mostPopular(){
        ApiUtils.getServiceClass().popularMovies(API_KEY,"1").enqueue(new Callback<MostPopularModel>() {
            @Override
            public void onResponse(Call<MostPopularModel> call, Response<MostPopularModel> response) {
                if(response.isSuccessful()){
                    movieList =new ArrayList<>();
                    for(int i=0;i<response.body().getMovieResults().size();i++){
                        movieList.add(i,new RecyclerPopular(response.body().getMovieResults().get(i).getTitle()));
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
}
