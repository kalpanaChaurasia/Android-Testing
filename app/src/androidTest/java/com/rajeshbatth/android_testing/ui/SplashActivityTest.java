package com.rajeshbatth.android_testing.ui;

import com.rajeshbatth.android_testing.MyIdlingResource;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.TestUtils;
import com.rajeshbatth.android_testing.account.AccountsManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(
            SplashActivity.class);

    @Before
    public void setUp() {
        Log.d(SplashActivity.TAG, "setUp ");
        MyIdlingResource idlingResource = new MyIdlingResource();
        SplashActivity activity = mActivityTestRule.getActivity();
        activity.setTaskListener(idlingResource);
        Espresso.registerIdlingResources(idlingResource);
    }


    @Test
    public void testSignInActivityLaunch() {
        Log.d(SplashActivity.TAG, "testNextActivityLaunched test");

        SplashActivity activity = mActivityTestRule.getActivity();
        AccountsManager accountsManager = Mockito.mock(AccountsManager.class);
        activity.mAccountsManager = accountsManager;

        Mockito.when(accountsManager.isUserLoggedIn()).thenReturn(false);
        onView(withId(R.id.splash_msg)).perform(click());
//        TestUtils.safeSleep(5000); //I want this code gone
        Mockito.verify(accountsManager).isUserLoggedIn();
        onView(withId(R.id.sign_in_button)).check(matches(isDisplayed()));
    }

   /* @Test
    public void testHomeActivityLaunch() {
        Log.d(SplashActivity.TAG, "testNextActivityLaunched test");

        SplashActivity activity = mActivityTestRule.getActivity();
        AccountsManager accountsManager = Mockito.mock(AccountsManager.class);
        activity.mAccountsManager = accountsManager;

        Mockito.when(accountsManager.isUserLoggedIn()).thenReturn(true);
        TestUtils.safeSleep(5000); //I want this code gone
        Mockito.verify(accountsManager).isUserLoggedIn();
        onView(withText(R.string.home)).check(matches(isDisplayed()));
    }*/
}
