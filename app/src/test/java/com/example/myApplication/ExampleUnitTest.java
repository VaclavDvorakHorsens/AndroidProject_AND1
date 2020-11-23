package com.example.myApplication;

import android.app.Application;
import android.content.Context;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.example.myApplication.Model.Call.CallModel;
import com.example.myApplication.Model.Call.PhoneNumber;
import com.example.myApplication.Model.Diary.DiaryItem;
import com.example.myApplication.Model.Diary.DiaryModel;
import com.example.myApplication.Model.Email.EmailModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */





@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    private DatabaseReference mockedDatabaseReference;
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();
    @Mock
    Context mockContext;



    @Test
    public void checkEmail() throws InterruptedException {
        EmailModel.getInstance().retrieveEmail();
        /*wait(4000);*/
        Thread.sleep(4000);
        /*new Timer().schedule(new TimerTask(){
                    @Override
                    public void run(){
                        assertEquals("MOM",EmailModel.getInstance().getEmail().getValue().getEmailSender());
                    }

                }, 4000);*/
        String a=EmailModel.getInstance().getEmail().getValue().getEmailSender();
        assertEquals("MOM",a);
   }

    @Test
    public void checkCall()
    {
        String b=CallModel.getInstance().getPhoneNumber("mom");
        assertEquals("MOM",b);
    }

    @Test
    public void diary()
    {
        Application application=new Application();
        DiaryModel.getInstance(application).getAllDiaryItems().getValue().clear();
        DiaryItem item=new DiaryItem("testDate","testEntry");
        DiaryModel.getInstance(application).insert(item);
        LiveData<List<DiaryItem>> a=DiaryModel.getInstance(application).getAllDiaryItems();
        DiaryItem newItem= a.getValue().get(0);
    }




}