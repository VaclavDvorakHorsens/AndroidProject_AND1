package com.example.myApplication.ModelView.Diary;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myApplication.Model.Diary.DiaryModel;
import com.example.myApplication.Model.Diary.DiaryItem;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {

    private DiaryModel diaryModel;


    public DiaryViewModel(Application application) {
        super(application);
        diaryModel=DiaryModel.getInstance(application);

    }


    //constructor that accepts Application object

    //add new item
    public void addDiaryItem(DiaryItem diaryItem)
    {
        diaryModel.insert(diaryItem);

      /*  DiaryModel.getInstance().insertDiaryItem(diaryItem);*/
    }


    //update item
    public void updateDiaryItem(DiaryItem diaryItem)
    {
        diaryModel.update(diaryItem);

        /*  DiaryModel.getInstance().insertDiaryItem(diaryItem);*/
    }


    //delete item
    public void deleteDiaryItem(DiaryItem diaryItem)
    {
        diaryModel.delete(diaryItem);

        /*  DiaryModel.getInstance().insertDiaryItem(diaryItem);*/
    }

    //get number of items
    public int getItemCount()
    {
        List<DiaryItem> currentDiaryItemList= diaryModel.getAllDiaryItems().getValue();
        if(currentDiaryItemList!=null)
             return currentDiaryItemList.size();
        else return 0;
      /* return DiaryModel.getInstance().getAllDiaryItems().getValue().size();*/
    }

    //get all items
    public LiveData<List<DiaryItem>> getAllItems()
    {

        return diaryModel.getAllDiaryItems();
        /*return DiaryModel.getInstance().getAllDiaryItems();*/
    }



}
