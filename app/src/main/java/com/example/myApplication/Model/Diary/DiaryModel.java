package com.example.myApplication.Model.Diary;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class DiaryModel {

    private DiaryDao diaryDao;
    private static DiaryModel instance;
    private LiveData<List<DiaryItem>> allDiaryItems;

   /* private MutableLiveData<List<DiaryItem>> diaryItems;*/

    //private constructor
    private DiaryModel(Application application){
        /*diaryItems = new MutableLiveData<>();
        List<DiaryItem> newList = new ArrayList<>();
        diaryItems.setValue(newList);*/
        DiaryDatabase diaryDatabase=DiaryDatabase.getInstance(application);
        diaryDao=diaryDatabase.diaryDao();
        allDiaryItems = diaryDao.getAllDiaryItems();

    }


    //singleton, get instance of THIS
    public static synchronized DiaryModel getInstance(Application application){
        if(instance == null)
            instance = new DiaryModel(application);
        return instance;
    }



    /*public LiveData<List<DiaryItem>> getAllDiaryItems(){
        return diaryItems;
    }*/

    //return all diary items
    public LiveData<List<DiaryItem>> getAllDiaryItems(){
        return allDiaryItems;
    }


    /*public void insertDiaryItem(DiaryItem diaryItem) {
        List<DiaryItem> currentDiaryItems = diaryItems.getValue();
        currentDiaryItems.add(diaryItem);
        diaryItems.setValue(currentDiaryItems);
    }*/

    public void insert(DiaryItem diaryItem) {
        new InsertDiaryItemAsync(diaryDao).execute(diaryItem);
    }


    public void update(DiaryItem diaryItem) {
        new UpdateDiaryItemAsync(diaryDao).execute(diaryItem);
    }


    public void delete(DiaryItem diaryItem) {
        new DeleteDiaryItemAsync(diaryDao).execute(diaryItem);
    }


    /*public void deleteAllDiaryItems(){
        List<DiaryItem> newList = new ArrayList<>();
        diaryItems.setValue(newList);
    }*/
    public void deleteAll(){
        new DeleteAllDiaryItemsAsync(diaryDao).execute();
    }


    private static class InsertDiaryItemAsync extends AsyncTask<DiaryItem,Void,Void> {
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





    private static class UpdateDiaryItemAsync extends AsyncTask<DiaryItem,Void,Void> {
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




    private static class DeleteDiaryItemAsync extends AsyncTask<DiaryItem,Void,Void> {
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






    private static class DeleteAllDiaryItemsAsync extends AsyncTask<Void,Void,Void> {
        private DiaryDao diaryDao;

        private DeleteAllDiaryItemsAsync(DiaryDao diaryDao) {
            this.diaryDao = diaryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            diaryDao.deleteAll();
            return null;
        }

    }

}
