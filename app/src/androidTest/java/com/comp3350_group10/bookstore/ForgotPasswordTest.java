/**
 * As an employee, I want to be able to reset my password if I forgot.
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

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ForgotPasswordTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){ TestHelper.setupHelper(); }

    @After
    public void tearDown(){
        TestHelper.tearDownHelper();
    }

    @Test
    public void forgotPassword() {
        Activity a = TestHelper.getActivity(activityTestRule);
        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.switchTo_login_button, 0);

        onView(withId(R.id.username)).perform(typeText(TestHelper.adminID));
        closeSoftKeyboard();
        onView(withId(R.id.forgot_pw_button)).perform(click());

        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.switchTo_login_button, 0);

        onView(withId(R.id.username)).perform(typeText(TestHelper.adminID));
        onView(withId(R.id.password)).perform(typeText("12345678"));    //default password
        onView(withId(R.id.loginButton)).perform(click());

        assertEquals("Failed to log in with reset password", UserDataHandler.currentUser.getUserID(), TestHelper.adminID);
    }
}