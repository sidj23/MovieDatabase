package com.sid.moviedatabase.RoomDatabase;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addMovies(MostPopEntity mostPopEntity);

    @Query("select * from mostpopularmovies")
    public List<MostPopEntity> getMostPop();

}

