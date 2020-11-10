package com.example.myApplication.ModelView.Diary;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.myApplication.Model.Diary.DiaryModel;
import com.example.myApplication.Model.Diary.DiaryItem;
import java.util.List;


//THIS servers as a bridge between Model and View
public class DiaryViewModel extends AndroidViewModel {

    private DiaryModel diaryModel;


    //constructor that accepts Application object and calls for Model
    public DiaryViewModel(Application application) {
        super(application);
        diaryModel = DiaryModel.getInstance(application);
    }


    //add new item
    public void addDiaryItem(DiaryItem diaryItem) {
        diaryModel.insert(diaryItem);
    }


    //update item
    public void updateDiaryItem(DiaryItem diaryItem) {
        diaryModel.update(diaryItem);
    }


    //delete item
    public void deleteDiaryItem(DiaryItem diaryItem) {
        diaryModel.delete(diaryItem);
    }


    //get all items
    public LiveData<List<DiaryItem>> getAllItems() {
        return diaryModel.getAllDiaryItems();
    }


}
