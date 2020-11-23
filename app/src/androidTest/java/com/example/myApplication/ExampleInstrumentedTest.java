package com.example.myApplication;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.example.myApplication.Model.Email.EmailModel;

import com.example.myApplication.Model.Email.EmailModel;
import com.example.myApplication.View.Email.EmailFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {



    @Rule
    public ActivityTestRule<SignInActivity> activity = new ActivityTestRule<>(SignInActivity.class);
    public FragmentActivity email=new FragmentActivity();


    @Test
    public void sendEmail() {
        /*onView(withId(R.id.emailAdd).perform(typeText("vasekdv@seznam.cz")));
        onView(withId(R.id.greet_button)).perform(click());
        onView(withText("Hello Steve!")).check(matches(isDisplayed()));*/
    }


}