package com.example.myApplication.Model.Diary;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "diary_table")
public class DiaryItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String diaryDate;
    private String diaryDateDescription;


    //constructor
    public DiaryItem(String diaryDate, String diaryDateDescription) {
        this.diaryDate = diaryDate;
        this.diaryDateDescription = diaryDateDescription;
    }


    //constructor that accepts id from adapter list item cache, for updating/deleting purposes
    @Ignore
    public DiaryItem(int id, String diaryDate, String diaryDateDescription) {
        this.diaryDate = diaryDate;
        this.diaryDateDescription = diaryDateDescription;
        this.id = id;
    }


    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(String diaryDate) {
        this.diaryDate = diaryDate;
    }

    public String getDiaryDateDescription() {
        return diaryDateDescription;
    }

    public void setDiaryDateDescription(String diaryDateDescription) {
        this.diaryDateDescription = diaryDateDescription;
    }


}
