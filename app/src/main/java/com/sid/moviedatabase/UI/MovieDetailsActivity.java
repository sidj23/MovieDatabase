package com.sid.moviedatabase.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.sid.moviedatabase.R;
import com.sid.moviedatabase.Retrofit.ApiUtils;
import com.sid.moviedatabase.RetrofitModel.MostPopularModel;
import com.sid.moviedatabase.RetrofitModel.MovieDetailsModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.moviedatabase.Constants.API_KEY;
import static com.sid.moviedatabase.Constants.IMG_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView imgProfilePost,imgBackPost;
    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        setTitle("Movie Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgProfilePost=findViewById(R.id.img_profile_poster);
        imgBackPost=findViewById(R.id.img_back_post);
        tvTitle=findViewById(R.id.tv_title);
        Intent get = getIntent();
        getDetails(get.getStringExtra("movieId"));

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
                        tvTitle.setText(response.body().getTitle());

                        Picasso.get()
                                .load(IMG_URL+response.body().getImgBackUrl())
                                .into(imgBackPost);
                        Picasso.get()
                                .load(IMG_URL+response.body().getImgProfileUrl())
                                .into(imgProfilePost);



                    }
                }
            }

            @Override
            public void onFailure(Call<MovieDetailsModel> call, Throwable t) {

            }
        });
    }
}
