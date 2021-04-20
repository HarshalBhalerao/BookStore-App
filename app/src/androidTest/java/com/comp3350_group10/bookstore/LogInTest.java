/**
 * As employee, I want to be able to log in to my account and use extra functions for staffs
 */

package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.TestHelper;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.comp3350_group10.bookstore.business.UserDataHandler.currentUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LogInTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){ TestHelper.setupHelper(); }

    @After
    public void tearDown(){
        TestHelper.tearDownHelper();
    }

    @Test
    public void login() {
        TestHelper.loginTestUser(activityTestRule);

        assertEquals(TestHelper.adminID.toLowerCase(), currentUser.getUserID().toLowerCase());
    }

    @Test
    public void logout(){
        Activity a = TestHelper.getActivity(activityTestRule);
        TestHelper.loginTestUser(activityTestRule);

        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.main_logout_button, 0);

        assertTrue("Logout failed", currentUser == null);
    }
}