package com.example.popular_movies_stage4;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.popular_movies_stage4.module.AppDatabase;
import com.example.popular_movies_stage4.module.MovieModule;
import java.util.ArrayList;
import java.util.List;
// TODO (56) Implement MovieAdapter.MovieAdapterOnClickHandler from the MainActivity

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.MovieAdapterOnClickHandler

        {    private AppDatabase mDb;
            MainViewModel viewModel;
            Observer<List<MovieModule>> Observer;


            // todo (1) Add a private RecyclerView variable called movieRecyclerView
    private RecyclerView movieRecyclerView;
    // todo (2) Add a private movieAdapter variable called MovieAdapter
    private MovieAdapter movieAdapter;

    private List<MovieModule> movies = new ArrayList<>();



    String theQuery = "popular";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb=AppDatabase.getInstance(getApplicationContext());
        // todo (3) Use findViewById to get a reference to the RecyclerView

        movieRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        // todo(4) Set the layoutManager on movieRecyclerView
        movieRecyclerView.setHasFixedSize(true);
        // TODO (58) Pass in this as the ListItemClickListener to the movieAdapter constructor

        movieAdapter = new MovieAdapter(this, movies,this);
        movieRecyclerView.setAdapter(movieAdapter);

        viewModel=ViewModelProviders.of(this).get(MainViewModel.class);
        showMovieData();



    }



    private void showMovieData() {

        if(getSelected().equals(getString(R.string.pref_popular_value))){
            theQuery="popular";
            viewModel.getMoviesService(theQuery).observe(this,Observer=new Observer<List<MovieModule>>() {
                @Override
                public void onChanged(@Nullable List<MovieModule> movieModules) {
                    if (movieModules!=null) {
                        movies.clear();
                        movies.addAll(movieModules);
                        movieAdapter.notifyDataSetChanged();

                    }

                }
            });
        }
        else if(getSelected().equals(getString(R.string.pref_top_rate_value))){
            theQuery="top_rated";
            viewModel.getMoviesService(theQuery).observe(this,Observer=new Observer<List<MovieModule>>() {
                @Override
                public void onChanged(@Nullable List<MovieModule> movieModules) {
                    if (movieModules!=null) {
                        movies.clear();
                        movies.addAll(movieModules);
                        movieAdapter.notifyDataSetChanged();

                    }

                }
            });
        }
        else{
                viewModel.loadMovies().observe(this, Observer=new Observer<List<MovieModule>>() {
                    @Override
                    public void onChanged(@Nullable List<MovieModule> movieModules) {
                        if (movieModules!=null) {
                            movies.clear();
                            movies.addAll(movieModules);
                            movieAdapter.notifyDataSetChanged();

                        }

                    }
                });
        }
    }   // TODO (59) Override ListMoviePosterClickListener's onMoviePosterClick method


            @Override
             protected void onRestart() {
                super.onRestart();

                    showMovieData();

            }

            @Override
        public void onMoviePosterClick(int itemInList) {
        launchDetailsMovie(itemInList);
    }
        void launchDetailsMovie(int position){
        Context context = MainActivity.this;
        Class destinationActivity = DetailsMovie.class;
        Intent startDetailsMoviIntent = new Intent(context, destinationActivity);
        startDetailsMoviIntent.putExtra(Intent.EXTRA_TEXT, position);
        startDetailsMoviIntent.putExtra("title", movies.get(position).getTitle());
        startDetailsMoviIntent.putExtra("vote",movies.get(position).getVote());
        startDetailsMoviIntent.putExtra("release",movies.get(position).getRelease());
        startDetailsMoviIntent.putExtra("overview", movies.get(position).getOverview());
        startDetailsMoviIntent.putExtra("poster", movies.get(position).getPosterbig());
        startDetailsMoviIntent.putExtra("postersmall", movies.get(position).getPoster());
        startDetailsMoviIntent.putExtra("id",movies.get(position).getId());

        startActivity(startDetailsMoviIntent);
    }




    //get help from sunshine example
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_movie,menu);
        return true;}
    // TODO (6) Return true to display the menu

    // TODO (7) Override onOptionsItemSelected to handle clicks on the refresh button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_settings){
            Intent startSettingActivity =new Intent(this ,SettingsActivity.class);
            startActivity(startSettingActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
            private String getSelected() {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                return sharedPrefs.getString(getString(R.string.pref_sort_key), getString(R.string.pref_popular_value));
            }


}

