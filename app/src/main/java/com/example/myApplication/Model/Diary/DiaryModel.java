package com.example.myApplication.Model.Diary;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;


//diary model class that holds access to database
public class DiaryModel {

    private DiaryDao diaryDao;
    private static DiaryModel instance;
    private LiveData<List<DiaryItem>> allDiaryItems;


    //private constructor
    private DiaryModel(Application application) {
        DiaryDatabase diaryDatabase = DiaryDatabase.getInstance(application);
        diaryDao = diaryDatabase.diaryDao();
        allDiaryItems = diaryDao.getAllDiaryItems();
    }


    //singleton, get instance of THIS
    public static synchronized DiaryModel getInstance(Application application) {
        if (instance == null)
            instance = new DiaryModel(application);
        return instance;
    }


    //return all diary items
    public LiveData<List<DiaryItem>> getAllDiaryItems() {
        return allDiaryItems;
    }


    //insert diary item to database via async method
    public void insert(DiaryItem diaryItem) {
        new InsertDiaryItemAsync(diaryDao).execute(diaryItem);
    }

    //update diary item in database via async method
    public void update(DiaryItem diaryItem) {
        new UpdateDiaryItemAsync(diaryDao).execute(diaryItem);
    }

    //delete diary item in database via async method
    public void delete(DiaryItem diaryItem) {
        new DeleteDiaryItemAsync(diaryDao).execute(diaryItem);
    }


    //async insert to database so the GUI will not get blocked for user
    private static class InsertDiaryItemAsync extends AsyncTask<DiaryItem, Void, Void> {
        private DiaryDao diaryDao;

        private InsertDiaryItemAsync(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryItem... diaryItems) {
            diaryDao.insert(diaryItems[0]);
            return null;
        }
    }


    //async update to database so the GUI will not get blocked for user
    private static class UpdateDiaryItemAsync extends AsyncTask<DiaryItem, Void, Void> {
        private DiaryDao diaryDao;

        private UpdateDiaryItemAsync(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryItem... diaryItems) {
            diaryDao.update(diaryItems[0]);
            return null;
        }
    }


    //async delete in database so the GUI will not get blocked for user
    private static class DeleteDiaryItemAsync extends AsyncTask<DiaryItem, Void, Void> {
        private DiaryDao diaryDao;

        private DeleteDiaryItemAsync(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(DiaryItem... diaryItems) {
            diaryDao.delete(diaryItems[0]);
            return null;
        }
    }

}
