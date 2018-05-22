package com.exercise.androidproficiencyexercise;

import android.content.ClipData;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.exercise.androidproficiencyexercise.android.mvp.main.home.HomeActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by Akash on 22-05-2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewSampleTest {

    private static final int ITEM_BELOW_THE_FOLD = 40;

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(
            HomeActivity.class);

    @Test
    public void matcher() {
       onView(withId(R.id.info_recyclerview))
                .check(matches(hasDescendant(withText("No Discription available"))));
    }

    @Test
    public void specificchild() {
        onView(withId(R.id.info_recyclerview)).perform(scrollToPosition(5));
    }


    @Test
    public void scrollToPositionTest() {
        onView(withId(R.id.info_recyclerview)).perform(scrollToPosition(5));
        onView(withText("Eh")).check(matches(isDisplayed()));
    }
}
