package com.example.popular_movies_stage4.module;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DAOmovies {
    @Query("SELECT * FROM movieFavorite")
    LiveData<List<MovieModule>> loadFavorites();
    @Query("SELECT * FROM movieFavorite")
    //LiveData<List<MovieModule>> loadAllFavorite();
    List<MovieModule> loadAllFavorite();
    @Insert
    void insertFavorite(MovieModule movie);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorite(MovieModule movie);
    @Query("DELETE FROM movieFavorite WHERE title = :id")
    void deleteFavorite(String id);
    //Todo second change
    @Query("Select * from movieFavorite where title = :id")
     MovieModule loadMovie (String id);
}













