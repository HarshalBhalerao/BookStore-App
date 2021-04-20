/**
 * As a user, I want to find all the genre specific novels.
 */

package com.comp3350_group10.bookstore;

import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.comp3350_group10.bookstore.TestUtil.TestHelper;
import com.comp3350_group10.bookstore.presentation.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SortTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void sortBooks(){
        onView(withId(R.id.searchBar)).perform(click());
        onView(withId(R.id.searchBar)).perform(typeText("da"));
        Activity activity = TestHelper.getActivity(activityTestRule);

        TableLayout table = activity.findViewById(R.id.bookListTable);
        TableRow row = (TableRow) table.getChildAt(0);
        TextView textView = (TextView)row.getChildAt(1);

        boolean angels_and_demons = textView.getText().toString().contains("Angels and Demons");

        row = (TableRow) table.getChildAt(1);
        textView = (TextView)row.getChildAt(1);

        boolean breaking_dawn = textView.getText().toString().contains("Breaking Dawn");

        row = (TableRow) table.getChildAt(2);
        textView = (TextView)row.getChildAt(1);

        boolean da_vinci = textView.getText().toString().contains("The Da Vinci Code");

        assertTrue("Default sorting has failed.", angels_and_demons && breaking_dawn && da_vinci);
    }
}
