package com.example.myApplication.Model.Diary;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.example.navigationdrawer.R;


@Entity(tableName = "diary_table")
public class DiaryItem {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String diaryDate;
    private String diaryDateDescription;
    /*private int diaryIconId;*/


    public DiaryItem(String diaryDate,String diaryDateDescription)
    {
        this.diaryDate=diaryDate;
        this.diaryDateDescription=diaryDateDescription;
        /*this.diaryIconId= R.drawable.diary;*/
    }

    @Ignore
    public DiaryItem(int id,String diaryDate,String diaryDateDescription)
    {
        this.diaryDate=diaryDate;
        this.diaryDateDescription=diaryDateDescription;

        this.id=id;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDiaryDate() {
        return diaryDate;
    }
    public void setDiaryDate(String diaryDate){this.diaryDate=diaryDate;}

    public String getDiaryDateDescription()
    {
        return diaryDateDescription;
    }
    public void setDiaryDateDescription(String diaryDateDescription){this.diaryDateDescription=diaryDateDescription;}

  /*  public int getDiaryIconId()
    {
        return diaryIconId;
    }
    public void setDiaryIconId(int diaryIconId){this.diaryIconId=diaryIconId;}*/
}
