/**
 * As a manager, I want to remove account for resigned employees.
 */

package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.Exceptions.UserNotFoundException;
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RemoveUserTest {
    UserDataHandler uHandler;
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        uHandler = new UserDataHandler();
        TestHelper.setupHelper();
        uHandler.createNewUser("new","new@new.com","12345678",false);
    }

    @After
    public void tearDown() {
        TestHelper.tearDownHelper();
        try {
            uHandler.deleteUser("new@new.com");
        }
        catch(Exception e){
            System.out.println("Failed to delete user in RemoveUserTest teardown.");
        }
    }

    @Test
    public void removeUser() {
        TestHelper.loginTestUser(activityTestRule);

        Activity a = TestHelper.getActivity(activityTestRule);
        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.switchTo_user_settings_button, 0);

        onView(withId(R.id.switchto_remove_user_button)).perform(click());

        onView(withId(R.id.delete_user_id_text)).perform(typeText("new@new.com"));
        pressBack();

        onView(withId(R.id.remove_user_button)).perform(click());

        //Test
        try {
            uHandler.logIn("new@new.com", "12345678");
            fail();
        }
        catch(UserNotFoundException e){
            //passed if not able to log in with the removed user
            assertTrue(true);
        }
    }
}