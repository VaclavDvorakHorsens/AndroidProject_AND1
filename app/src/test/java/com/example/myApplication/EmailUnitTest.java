package com.example.myApplication;


import com.example.myApplication.ModelView.Email.EmailViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.assertEquals;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(JUnit4.class)
public class EmailUnitTest {


    private EmailViewModel emailViewModel;

    @Before
    public void setUp()
    {
        emailViewModel=new EmailViewModel();
    }

    @After
    public void cleanUp()
    {
        emailViewModel=null;
    }



    //test wrong email address format with no @
    @Test
    public void validateInvalidEmailAddress()
    {
        assertEquals(false,emailViewModel.validateEmail("subject","wrongEmailAddress","emailBody"));
    }

    //test wrong email address format, empty
    @Test
    public void validateInvalidEmptyEmailAddress()
    {
        assertEquals(false,emailViewModel.validateEmail("subject","","emailBody"));
    }

    //test correct email address format
    @Test
    public void validateValidEmailAddress()
    {
        assertEquals(true,emailViewModel.validateEmail("subject","correct@gmail.dk","emailBody"));
    }

    //test invalid email subject, empty
    @Test
    public void validateInValidEmailSubject()
    {
        assertEquals(false,emailViewModel.validateEmail("","correct@gmail.dk","emailBody"));
    }

    //test valid email subject
    @Test
    public void validateValidEmailSubject()
    {
        assertEquals(true,emailViewModel.validateEmail("subject","correct@gmail.dk","emailBody"));
    }

}