package com.sid.moviedatabase.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sid.moviedatabase.Constants.API_KEY;

public class MovieListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MovieListAdapter movieListAdapter;
    List<RecyclerMovieListModel> movieList;
    ProgressBar progressBar;
    private boolean isFetchingMovies;
    private int currentPage = 1;
    Intent get;
    String sortingType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView=findViewById(R.id.recycler_movie_list);
        progressBar=findViewById(R.id.progress_ml);
        get = getIntent();


        LinearLayoutManager layoutManager = new LinearLayoutManager(MovieListActivity.this, LinearLayout.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        switch (get.getStringExtra("type")){
            case "mostPopular" :
                mostPopular(currentPage,"normal");
                setupOnScrollListener("mostPopular","normal");
                setTitle("Most Popular");
                break;
            case "inTheatre":
                inTheatre(currentPage,"normal");
                setupOnScrollListener("inTheatre","normal");
                setTitle("In Theatre");
                break;
            case "comingSoon":
                comingSoon(currentPage,"normal");
                setupOnScrollListener("comingSoon","normal");
                setTitle("Coming Soon");
                break;
            case "topRated":
                topRated(currentPage,"normal");
                setupOnScrollListener("topRated","normal");
                setTitle("Top Rated");
                break;
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {
            finish();
            return true;
        }
        else if(item.getItemId() == R.id.sort){
            showSortMenu();
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }

    }

    private void showSortMenu() {
        PopupMenu sortMenu = new PopupMenu(this, findViewById(R.id.sort));

        sortMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                /*
                 * Every time we sort, we need to go back to page 1
                 */
                currentPage = 1;

                switch (item.getItemId()) {
                    case R.id.name:
                        setupOnScrollListener(get.getStringExtra("type"),"menu");
                        sortingType="name";
                        return true;
                    case R.id.top_rated:
                        setupOnScrollListener(get.getStringExtra("type"),"menu");
                        sortingType="rating";
                        return true;
                    case R.id.date_desc:
                        setupOnScrollListener(get.getStringExtra("type"),"menu");
                        sortingType="date_desc";
                        return true;
                    case R.id.date_asc:
                        setupOnScrollListener(get.getStringExtra("type"),"menu");
                        sortingType="date_asc";
                        return true;
                    default:
                        return false;
                }
            }
        });

        sortMenu.inflate(R.menu.menu_sort);
        sortMenu.show();
    }


    private void setupOnScrollListener(final String type,final String sortType) {
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = manager.getItemCount();
                int visibleItemCount = manager.getChildCount();
                int firstVisibleItem = manager.findFirstVisibleItemPosition();

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    if (!isFetchingMovies) {
                        switch (type){
                            case "mostPopular" :
                                mostPopular(currentPage+1,sortType);
                                setTitle("Most Popular");
                                break;
                            case "inTheatre":
                                inTheatre(currentPage+1,sortType);
                                setTitle("In Theatre");
                                break;
                            case "comingSoon":
                                comingSoon(currentPage+1,sortType);
                                setTitle("Coming Soon");
                                break;
                            case "topRated":
                                topRated(currentPage+1,sortType);
                                setTitle("Top Rated");
                                break;
                        }
                    }
                }
            }
        });
    }



    public void mostPopular(final int page, final String type){
        isFetchingMovies = true;
        ApiUtils.getServiceClass().popularMovies(API_KEY,page).enqueue(new Callback<MostPopularModel>() {
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
                        Log.d("mlresponse", "onResponse: "+response.body().getMovieResults().get(i).getMovieId());
                    }

                    if(type.equals("normal")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.appendMovies(movieList);
                        }
                    }
                    else if(type.equals("menu")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.sortedList(movieList,sortingType);
                        }
                    }


                    currentPage = page;
                    isFetchingMovies = false;

                    // movieListAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<MostPopularModel> call, Throwable t) {

            }
        });
    }

    private void inTheatre(final int page,final String type) {
        isFetchingMovies = true;
        ApiUtils.getServiceClass().inTheatreMovies(API_KEY,page).enqueue(new Callback<InTheatreModel>() {
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


                    if(type.equals("normal")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.appendMovies(movieList);
                        }
                    }
                    else if(type.equals("menu")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.sortedList(movieList,sortingType);
                        }
                    }
                    currentPage = page;
                    isFetchingMovies = false;

                    // movieListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<InTheatreModel> call, Throwable t) {

            }
        });
    }

    private void comingSoon(final int page,final String type) {
        isFetchingMovies = true;
        ApiUtils.getServiceClass().comingSoon(API_KEY,page).enqueue(new Callback<ComingSoonModel>() {
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

                    if(type.equals("normal")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.appendMovies(movieList);
                        }
                    }
                    else if(type.equals("menu")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.sortedList(movieList,sortingType);
                        }
                    }

                    currentPage = page;
                    isFetchingMovies = false;

                    // movieListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ComingSoonModel> call, Throwable t) {

            }
        });
    }

    private void topRated(final int page,final String type) {
        isFetchingMovies = true;
        ApiUtils.getServiceClass().topRated(API_KEY,page).enqueue(new Callback<TopRatedModel>() {
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

                    if(type.equals("normal")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.appendMovies(movieList);
                        }
                    }
                    else if(type.equals("menu")){
                        if (movieListAdapter == null) {
                            movieListAdapter = new MovieListAdapter(MovieListActivity.this,movieList);
                            recyclerView.setAdapter(movieListAdapter);
                            recyclerView.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else
                        {
                            movieListAdapter.sortedList(movieList,sortingType);
                        }
                    }
                    currentPage = page;
                    isFetchingMovies = false;

                    // movieListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TopRatedModel> call, Throwable t) {

            }
        });
    }


}
//    public void sortList(){
//        Collections.sort(movieList, new Comparator<RecyclerMovieListModel>(){
//        @Override
//        public int compare(RecyclerMovieListModel o1, RecyclerMovieListModel o2) {
//            return o1.getName().compareToIgnoreCase(o2.getName());
//            }
//        });
//        movieListAdapter.sortedList(movieList);
//    }