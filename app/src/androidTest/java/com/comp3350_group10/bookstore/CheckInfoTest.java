package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.os.Looper;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.TestHelper;
import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CheckInfoTest {
    String empID, empPw;
    UserDataHandler uHandler;
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){
        if(Looper.myLooper()==null)
            Looper.prepare();

        empID = "emp@test.ca";
        empPw = "222222222";
        uHandler = new UserDataHandler();

        //make sure user not already in db
        try{
            uHandler.deleteUser(empID);
        }
        catch(Exception e){}

        uHandler.createNewUser("employee", empID, empPw,false);
    }

    @After
    public void tearDown(){
        TestHelper.tearDownHelper();
    }

    @Test
    public void checkInfo() {
        Activity a = TestHelper.getActivity(activityTestRule);

        onView(withId(R.id.searchBar)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Harry Potter and the Chamber of Secrets"));

        TableLayout table = a.findViewById(R.id.bookListTable);
        TableRow row = (TableRow) table.getChildAt(0);
        row.callOnClick();

        assertNotNull(BookDataHandler.currentBook);
    }
}