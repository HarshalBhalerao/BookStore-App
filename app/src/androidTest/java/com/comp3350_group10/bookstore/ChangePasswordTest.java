/**
 * As an employee, I want to be able to change my password
 */

package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.TestHelper;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangePasswordTest {
    UserDataHandler uHandler;
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup()
    {
        uHandler = new UserDataHandler();
        TestHelper.setupHelper();
    }

    @After
    public void tearDown(){ TestHelper.tearDownHelper(); }

    @Test
    public void changePassword() {
        TestHelper.loginTestUser(activityTestRule);

        Activity a = TestHelper.getActivity(activityTestRule);
        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.switchTo_user_settings_button, 0);

        onView(withId(R.id.oldPassword)).perform(typeText(TestHelper.adminPw));
        pressBack();
        onView(withId(R.id.newPassword)).perform(typeText("11111111"));
        pressBack();
        onView(withId(R.id.confirmNewPassword)).perform(typeText("11111111"));
        pressBack();

        onView(withId(R.id.user_change_password)).perform(click());

        uHandler.logIn(TestHelper.adminID,"11111111");
        assertEquals("Failed: Not able to log in as the new created user.", UserDataHandler.currentUser.getUserID(), TestHelper.adminID);
    }
}