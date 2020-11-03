package com.example.myApplication.Model.Diary;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DiaryItem.class}, version = 2)
public abstract class DiaryDatabase extends RoomDatabase {

    private static DiaryDatabase instance;

    public abstract DiaryDao diaryDao();

    public static synchronized DiaryDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DiaryDatabase.class, "diary_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
