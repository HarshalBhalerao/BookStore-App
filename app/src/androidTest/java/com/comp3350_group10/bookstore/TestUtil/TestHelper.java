package com.comp3350_group10.bookstore.TestUtil;

import android.app.Activity;
import android.os.Looper;
import android.view.Menu;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.comp3350_group10.bookstore.R;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import java.util.concurrent.atomic.AtomicReference;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.comp3350_group10.bookstore.business.UserDataHandler.currentUser;

public class TestHelper {
    public static String adminID, adminPw;
    public static UserDataHandler uHandler;

    public static void setupHelper() {
        if(Looper.myLooper()==null)
            Looper.prepare();

        adminID = "admin@test.ca";
        adminPw = "222222222";
        uHandler = new UserDataHandler();

        //make sure user not already in db
        try{
            uHandler.deleteUser(adminID);
        }
        catch(Exception e){}

        uHandler.createNewUser("admin", adminID, adminPw,true);
    }

    public static void tearDownHelper() {
        currentUser = null;
        try {
            uHandler.deleteUser(adminID);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void loginTestUser(ActivityScenarioRule<MainActivity> activityTestRule) {
        Activity a = TestHelper.getActivity(activityTestRule);
        Menu menu = new MenuBuilder(a);
        a.getMenuInflater().inflate(R.menu.main, menu);
        menu.performIdentifierAction(R.id.switchTo_login_button, 0);

        onView(withId(R.id.username)).perform(typeText(adminID));
        onView(withId(R.id.password)).perform(typeText(adminPw));
        onView(withId(R.id.loginButton)).perform(click());
    }

    public static <T extends Activity> T getActivity(ActivityScenarioRule<T> activityScenarioRule) {
        AtomicReference<T> activityRef = new AtomicReference<>();
        activityScenarioRule.getScenario().onActivity(activityRef::set);
        return activityRef.get();
    }
}
