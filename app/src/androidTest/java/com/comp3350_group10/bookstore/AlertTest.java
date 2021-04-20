/**
 * As an employee, I want to be able to return a book to the inventory.
 */

package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.TestHelper;
import com.comp3350_group10.bookstore.business.BookDataHandler;
import com.comp3350_group10.bookstore.business.IBookDataHandler;
import com.comp3350_group10.bookstore.business.UserDataHandler;
import com.comp3350_group10.bookstore.objects.IBook;
import com.comp3350_group10.bookstore.presentation.MainActivity;
import com.comp3350_group10.bookstore.presentation.UI_Handler.Notify;

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

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AlertTest
{
    UserDataHandler uHandler;

    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup(){
        TestHelper.setupHelper();
        uHandler = new UserDataHandler();
        uHandler.logIn(TestHelper.adminID, TestHelper.adminPw);
    }

    @After
    public void tearDown(){
        TestHelper.tearDownHelper();
    }

    @Test
    public void lowStockAlert() {
        Activity a = TestHelper.getActivity(activityTestRule);

        onView(withId(R.id.searchBar)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("Harry Potter and the Chamber of Secrets"));

        TableLayout table = a.findViewById(R.id.bookListTable);
        TableRow row = (TableRow) table.getChildAt(0);
        row.callOnClick();

        //set to 10 so alert is thrown when decreased
        onView(withId(R.id.change_stock_text)).perform(click());
        onView(withId(R.id.change_stock_text)).perform(typeText("10"));
        onView(withId(R.id.set_stock_button)).perform(click());
        pressBack();

        onView(withId(R.id.bookDetailsSaleButton)).perform(click());

        IBookDataHandler bookHandler = new BookDataHandler();
        IBook book = bookHandler.findBooks("Harry Potter and the Chamber of Secrets", true, "Title").get(0);

        assert(Notify.getLowStockNotified().get(0).getBookName().equals("Harry Potter and the Chamber of Secrets"));
    }
}
