package com.example.popular_movies_stage4;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.popular_movies_stage4.module.AppDatabase;
import com.example.popular_movies_stage4.module.MovieModule;
import com.example.popular_movies_stage4.utilities.JSONUtilities;
import com.example.popular_movies_stage4.utilities.NetworkUtilities;

import java.net.URL;
import java.util.List;

public class MainViewModel extends AndroidViewModel {


    MutableLiveData<List <MovieModule>> listMutableLiveData = new MutableLiveData<>();
    private LiveData<List<MovieModule>> favoriteMovies;

    AppDatabase database;
    int count=0;

    public MainViewModel(Application application) {
        super(application);
         database = AppDatabase.getInstance(this.getApplication());
        favoriteMovies=database.moviesDAO().loadFavorites();
    }
    public LiveData<List<MovieModule>> loadMovies() {
        return favoriteMovies;
    }


    /*public LiveData <List<MovieModule>> getMovies () {
                //load it from room
                new FavoriteAsyncTask().execute();
                return listMutableLiveData;

    }*/
    public LiveData <List<MovieModule>> getMoviesService (String query) {
        //load it from service
        new ServiceAsycTask().execute(query);
        return listMutableLiveData;

    }

    /*public class FavoriteAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            listMutableLiveData.postValue(database.moviesDAO().loadAllFavorite());
            Log.d("mainViewModel","the response in FavoriteAsyncTask"+(count+1));

            return null;
        }

    }*/

    public class ServiceAsycTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
                if (strings.length == 0) {
                    return null;
                }

                String queryTobulid = strings[0];
                URL movieRequestUrl = NetworkUtilities.buildUrl(queryTobulid);
                try {
                    String jsonMovieResponse = NetworkUtilities.getResponseFromHttpUrl(movieRequestUrl);
                    listMutableLiveData.postValue(JSONUtilities.parseMovieJson(jsonMovieResponse));
                } catch (Exception e) {
                    e.printStackTrace();
                }
              return null;
            }






    }
}
