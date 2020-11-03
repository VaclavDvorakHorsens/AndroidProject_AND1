package com.example.myApplication.Model.Diary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DiaryDao {

    @Insert
    void insert(DiaryItem diaryItem);

    @Update
    void update(DiaryItem diaryItem);

    @Delete
    void delete(DiaryItem diaryItem);



    @Query("DELETE FROM diary_table")
    void deleteAll();

    @Query("SELECT * FROM diary_table ORDER BY id DESC")
    LiveData<List<DiaryItem>> getAllDiaryItems();


}
