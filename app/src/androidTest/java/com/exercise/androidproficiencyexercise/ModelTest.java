package com.exercise.androidproficiencyexercise;

import android.support.test.rule.ActivityTestRule;

import com.exercise.androidproficiencyexercise.android.mvp.main.home.HomeActivity;
import com.exercise.androidproficiencyexercise.data.ListResponse;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Rule;

/**
 * Created by Akash on 22-05-2018.
 */

public class ModelTest extends TestCase {
    ListResponse listResponse;

    protected void setUp() throws Exception {
        super.setUp();
        listResponse = new ListResponse();
        listResponse.setTitle("About Canada");
    }

    public void testSurvey() {
        listResponse.toString();
    }

    public void testGetId() {
        String expected = "About Canada";
        String actual = listResponse.getTitle();
        Assert.assertEquals(expected, actual);
    }
}
