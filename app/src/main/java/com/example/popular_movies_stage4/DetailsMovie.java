package com.example.popular_movies_stage4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popular_movies_stage4.module.AppDatabase;
import com.example.popular_movies_stage4.module.MovieModule;
import com.example.popular_movies_stage4.module.ReviewModule;
import com.example.popular_movies_stage4.module.trailerModule;
import com.example.popular_movies_stage4.utilities.JSONUtilities;
import com.example.popular_movies_stage4.utilities.NetworkUtilities;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailsMovie extends AppCompatActivity {
    TextView movieTitle;
    TextView movieRate;
    TextView movieOverview;
    TextView movieDateRelease;
    ImageView moviePoster;
    TextView reviewMessageError;
    TextView trailerMessageError;
    Button  mButton;
    private AppDatabase mDb;
     String title,Poster,Posters,vote,overview,release;
      MovieModule receivedMovie;
      Boolean clickedButton =false;
    int id;
    // todo (1) Add a private RecyclerView variable called trailerRecyclerView
    private RecyclerView trailerRecyclerView;
    // todo (2) Add a private movieAdapter variable called trailerAdapter
    private TrailerAdapter trailerAdapter;
    private List<trailerModule> trailerModuleList = new ArrayList<>();
    // todo (1) Add a private RecyclerView variable called reviewRecyclerView
    private RecyclerView reviewRecyclerView;
    // todo (2) Add a private movieAdapter variable called reviewsAdapter
     ReviewsAdapter reviewsAdapter;
    private List<ReviewModule> Review = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO (3) Create AppDatabase member variable for the Database
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        // TODO (4) Initialize member variable for the data base
        mDb=AppDatabase.getInstance(getApplicationContext());


        movieTitle = (TextView)findViewById(R.id.tv_title);
          title = getIntent().getStringExtra("title");
        movieTitle.setText(title);
        //---------------------------------------------------
        moviePoster = (ImageView) findViewById(R.id.image_iv);
          Poster=getIntent().getStringExtra("poster");
        Picasso.get()
                .load(Poster)
                .into(moviePoster);
         Posters=getIntent().getStringExtra("postersmall");
        //-----------------------------------------------------
        movieRate = (TextView)findViewById(R.id.tv_vote);
         vote = getIntent().getStringExtra("vote");
        movieRate.setText(vote);
        //-----------------------------------------------------
        movieDateRelease = (TextView)findViewById(R.id.tv_date_release);
         release = getIntent().getStringExtra("release");
        movieDateRelease.setText(release);

        //----------------------------------------------------------
        movieOverview = (TextView)findViewById(R.id.tv_synopsis);
          overview = getIntent().getStringExtra("overview");
        movieOverview.setText(overview);
        //-----------------------------------------------------------
        id = getIntent().getIntExtra("id",0);
        //-----------------------------------------------------------
        trailerMessageError=(TextView)findViewById(R.id.tv_error_trailer);
        //-----------------------------------------------------------
        reviewMessageError=(TextView)findViewById(R.id.tv_error_review);
        //-----------------------------------------------------------
        receivedMovie =new MovieModule(title, Posters, Poster, vote, overview, release,id);

        mButton=(Button)findViewById(R.id.saveButton) ;
        new retrivedMovieAsyncTask().execute(receivedMovie);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new retrivedMovieAsyncTask().execute(receivedMovie);
                clickedButton =true;
            }}
            );
        //trailer
        //trailerModuleList trailerAdapter trailerRecyclerView
        trailerRecyclerView = (RecyclerView) findViewById(R.id.rv_trailer);
        LinearLayoutManager trailerLayoutManager = new LinearLayoutManager(this);
        //set the layout manager
        trailerRecyclerView.setLayoutManager(trailerLayoutManager);
        //changes in content shouldn't change the layout size
        trailerRecyclerView.setHasFixedSize(true);
        //set trailer adapter for recycler view
        trailerAdapter=new TrailerAdapter();
        trailerRecyclerView.setAdapter(trailerAdapter);

//---------------------------------------------------------
        reviewRecyclerView = (RecyclerView) findViewById(R.id.rv_review);
        LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(this);
        //set the layout manager
        reviewRecyclerView.setLayoutManager(reviewsLayoutManager);
        //changes in content shouldn't change the layout size
        reviewRecyclerView.setHasFixedSize(true);
        //set review adapter for recycler view
        reviewsAdapter=new ReviewsAdapter();
        reviewRecyclerView.setAdapter(reviewsAdapter);
        showReview();
        showTrailer();

    }
    void showReview(){
        new DoReviewTask().execute(id);
    }
    void showTrailer(){
        new DoTrailerTask().execute(id);
    }
    class DoReviewTask extends AsyncTask<Integer, Void, List<ReviewModule>> {
        @Override
        protected void onPreExecute(){
            Review.clear();
        }
        @Override
        protected List<ReviewModule> doInBackground(Integer... integers) {
            if (integers.length == 0) {
                return null;
            }
            int id= integers[0];

            URL url= NetworkUtilities.buildAnotherUrl(id,"reviews");
            try {
                String jsonReviewResponse = NetworkUtilities.getResponseFromHttpUrl(url);
                Review.addAll(JSONUtilities.parseReviewJson(jsonReviewResponse));
                return Review;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute( List<ReviewModule> ReviewData) {
            if (ReviewData.size() != 0) {
                reviewsAdapter.setmReview(ReviewData);

            }else{
                reviewMessageError.setVisibility(View.VISIBLE);}
        }

    }
    class DoTrailerTask extends AsyncTask<Integer, Void, List<trailerModule>> {
        @Override
        protected void onPreExecute(){
            //trailerModuleList.clear();
        }
        @Override
        protected List<trailerModule> doInBackground(Integer... integers) {
            if (integers.length == 0) {
                return null;
            }
            int id= integers[0];

            URL url= NetworkUtilities.buildAnotherUrl(id,"videos");
            try {
                String jsonTrailerResponse = NetworkUtilities.getResponseFromHttpUrl(url);
                trailerModuleList.addAll(JSONUtilities.parseTrailerJson(jsonTrailerResponse));
                return trailerModuleList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute( List<trailerModule> videoData) {
            if (videoData.size() != 0) {
                Log.d("trailerAdapter","the response here : "+videoData.get(0).getKey());
                //trailerModuleList trailerAdapter trailerRecyclerView

                trailerAdapter.setmTrailer(videoData,DetailsMovie.this);

            }else{
                Log.d("details","the response for error is : ");
                trailerMessageError.setVisibility(View.VISIBLE);}
        }

    }


    private class retrivedMovieAsyncTask extends AsyncTask<MovieModule, Void, MovieModule> {

        @Override
        protected MovieModule doInBackground(MovieModule... movie) {

            AppDatabase database = AppDatabase.getInstance(DetailsMovie.this);
            MovieModule selectedMovie = database.moviesDAO().loadMovie(movie[0].getTitle());


            return selectedMovie;
        }

        @Override
        protected void onPostExecute(MovieModule selectedMovie) {
            super.onPostExecute(selectedMovie);
            if(clickedButton){
                if (selectedMovie != null) {
                    deleteFavoriteMovie(receivedMovie);
                    mButton.setText(getResources().getString(R.string.Favorite));

                } else {
                    addFavoriteMovie(receivedMovie);
                    mButton.setText(getResources().getString(R.string.UNFavorite));

                }
            }
            else {
                if (selectedMovie != null) {
                   mButton.setText(getResources().getString(R.string.UNFavorite));
                } else {
                   mButton.setText(getResources().getString(R.string.Favorite));

                }
            }
        }
    }





    void addFavoriteMovie(final MovieModule movie){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.moviesDAO().insertFavorite(movie);
                finish();
            }
        });
    }
    void deleteFavoriteMovie(final MovieModule movie){

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.moviesDAO().deleteFavorite(movie.getTitle());
                finish();
            }
        });}


}
