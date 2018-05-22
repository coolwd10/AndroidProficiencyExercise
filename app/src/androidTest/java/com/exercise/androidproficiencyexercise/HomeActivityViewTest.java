package com.exercise.androidproficiencyexercise;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.view.View;

import com.exercise.androidproficiencyexercise.android.mvp.main.home.HomeActivity;
import com.exercise.androidproficiencyexercise.data.ListResponse;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Akash on 20-05-2018.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityViewTest extends InstrumentationTestCase {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule =
            new ActivityTestRule<>(HomeActivity.class);


    // check if View is either not visible or does not exist.
    @Test
    public void checkViewTest() {
        onView(withId(R.id.info_recyclerview)).check(matches(isNotDisplayed()));
    }

    //Check refresh btn clicked
    @Test
    public void refreshBtnViewTest() {
        onView(withId(R.id.simpleSwipeRefreshLayout)).perform(click());
    }


    private ViewAssertion matches(ViewAssertion notDisplayed) {
        return notDisplayed;
    }

    public ViewAssertion isNotDisplayed() {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noView) {
                if (view != null && isDisplayed().matches(view)) {
                    throw new AssertionError("View is present in the hierarchy and Displayed: "
                            + HumanReadables.describe(view));
                }
            }
        };
    }

    @Test
    public void incrementTwiceAndRotateScreen() {
        rotateScreen();
    }

    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation
                = context.getResources().getConfiguration().orientation;

        Activity activity = mActivityTestRule.getActivity();
        activity.setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

}
