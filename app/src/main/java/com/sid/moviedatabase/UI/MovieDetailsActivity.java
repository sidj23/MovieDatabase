package com.sid.moviedatabase.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sid.moviedatabase.Adapter.CastAdapter;
import com.sid.moviedatabase.Adapter.CrewAdapter;
import com.sid.moviedatabase.Model.RecyclerCastModel;
import com.sid.moviedatabase.Model.RecyclerCrewModel;
import com.sid.moviedatabase.R;
import com.sid.moviedatabase.Retrofit.ApiUtils;
import com.sid.moviedatabase.RetrofitModel.CastModels;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;
import com.sid.moviedatabase.RetrofitModel.MovieDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.moviedatabase.Constants.API_KEY;
import static com.sid.moviedatabase.Constants.IMG_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView imgProfilePost,imgBackPost;
    TextView tvTitle,tvRating,tvVotes,tvGenres,tvRelDate,tvPlot,tvDuration;

    List<RecyclerCastModel> castList;
    CastAdapter castAdapter;
    RecyclerView recyclerCast;

    List<RecyclerCrewModel> crewList;
    CrewAdapter crewAdapter;
    RecyclerView recyclerCrew;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setTitle("Movie Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        imgProfilePost=findViewById(R.id.img_md_profile_poster);
        imgBackPost=findViewById(R.id.img_md_back_post);
        tvTitle=findViewById(R.id.tv_md_title);
        tvRating=findViewById(R.id.tv_md_rating);
        tvVotes=findViewById(R.id.tv_md_votes);
        tvGenres=findViewById(R.id.tv_md_genre);
        tvRelDate=findViewById(R.id.tv_md_release_date);
        tvPlot=findViewById(R.id.tv_md_plot);
        tvDuration=findViewById(R.id.tv_md_duration);
        recyclerCast=findViewById(R.id.recycler_cast);
        recyclerCrew=findViewById(R.id.recycler_crew);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MovieDetailsActivity.this, LinearLayout.HORIZONTAL,false);
        recyclerCast.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayout = new LinearLayoutManager(MovieDetailsActivity.this,LinearLayout.HORIZONTAL,false);
        recyclerCrew.setLayoutManager(linearLayout);

        recyclerCast.setFocusable(false);
        recyclerCrew.setFocusable(false);

        Intent get = getIntent();
        getDetails(get.getStringExtra("movieId"));
        getCast(get.getStringExtra("movieId"));

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

    public void getDetails(final String id){
        ApiUtils.getServiceClass().getDetails(id,API_KEY).enqueue(new Callback<MovieDetailsModel>() {
            @Override
            public void onResponse(Call<MovieDetailsModel> call, Response<MovieDetailsModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getId().equals(id)){

                        StringBuilder builder = new StringBuilder();

                        for(int i=0;i<response.body().getGenre().size();i++){

                            if(i==(response.body().getGenre().size()-1)){
                                builder.append(response.body().getGenre().get(i).getName()+".");
                            }
                            else {
                                builder.append(response.body().getGenre().get(i).getName() + ", ");
                            }
                        }

                        int t=response.body().getRuntime();
                        int hours = t / 60; //since both are ints, you get an int
                        int minutes = t % 60;

                        tvDuration.setText(hours+"h "+minutes+"min");

                        tvTitle.setText(response.body().getTitle());

                        Picasso.get()
                                .load(IMG_URL+response.body().getImgBackUrl())
                                .into(imgBackPost);
                        Picasso.get()
                                .load(IMG_URL+response.body().getImgProfileUrl())
                                .into(imgProfilePost);

                        tvRating.setText(response.body().getRating()+"/10");

                        tvVotes.setText("("+response.body().getVote()+")");

                        tvGenres.setText(builder.toString());

                        tvRelDate.setText(response.body().getDate());

                        tvPlot.setText(response.body().getPlot());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsModel> call, Throwable t) {

            }
        });
    }

    public void getCast(final String id){
        ApiUtils.getServiceClass().getCast(id,API_KEY).enqueue(new Callback<CastModels>() {
            @Override
            public void onResponse(Call<CastModels> call, Response<CastModels> response) {
                if(response.isSuccessful()){
                    if(response.body().getId().equals(id)){
                        castList = new ArrayList<>();
                        for(int i=0;i<5;i++){
                            castList.add(i,new RecyclerCastModel(response.body().getCasts().get(i).getName(),
                                    response.body().getCasts().get(i).getCharacter(),
                                    response.body().getCasts().get(i).getImgProfile()));
                        }
                        castAdapter=new CastAdapter(MovieDetailsActivity.this,castList);
                        recyclerCast.setAdapter(castAdapter);
                        castAdapter.notifyDataSetChanged();

                        crewList = new ArrayList<>();
                        for(int i=0;i<5;i++){
                            crewList.add(i,new RecyclerCrewModel(response.body().getCrew().get(i).getName(),
                                    response.body().getCrew().get(i).getJob(),
                                    response.body().getCrew().get(i).getProfile()));
                        }
                        crewAdapter=new CrewAdapter(MovieDetailsActivity.this,crewList);
                        recyclerCrew.setAdapter(crewAdapter);
                        crewAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<CastModels> call, Throwable t) {

            }
        });
    }
}
