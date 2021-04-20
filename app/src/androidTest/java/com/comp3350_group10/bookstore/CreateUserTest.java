/**
 * As a manager, I want to create account for new employees
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
public class CreateUserTest {
    UserDataHandler uHandler;
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        uHandler = new UserDataHandler();
        TestHelper.setupHelper();
    }

    @After
    public void tearDown() {
        TestHelper.tearDownHelper();
        uHandler.deleteUser("newuser@mail.com");
    }

    @Test
    public void createUser() {
        TestHelper.loginTestUser(activityTestRule);

        Activity a = TestHelper.getActivity(activityTestRule);
        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.switchTo_user_settings_button, 0);
        onView(withId(R.id.switchto_create_user_button)).perform(click());

        onView(withId(R.id.new_name_text)).perform(typeText("New User"));
        pressBack();
        onView(withId(R.id.new_email_text)).perform(typeText("newuser@mail.com"));
        pressBack();
        onView(withId(R.id.new_password_text)).perform(typeText("12345678"));
        pressBack();

        onView(withId(R.id.create_user_button)).perform(click());

        //Test
        uHandler.logIn("newuser@mail.com","12345678");
        assertEquals("Failed: Not able to log in as the new created user.",UserDataHandler.currentUser.getRealName(), "New User");
    }
}