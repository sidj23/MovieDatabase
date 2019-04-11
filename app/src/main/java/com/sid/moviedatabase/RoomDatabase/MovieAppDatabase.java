package com.sid.moviedatabase.RoomDatabase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {MostPopEntity.class},version = 1,exportSchema = false)
public abstract class MovieAppDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
