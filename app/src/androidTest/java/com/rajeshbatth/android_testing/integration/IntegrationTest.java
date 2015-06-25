package com.rajeshbatth.android_testing.integration;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.ui.SplashActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.rajeshbatth.android_testing.TestUtils.matchToolbarTitle;
import static com.rajeshbatth.android_testing.TestUtils.safeSleep;
import static org.hamcrest.core.IsAnything.anything;

/**
 * Created by rajesh.j on 6/25/2015.
 */
@RunWith(AndroidJUnit4.class) public class IntegrationTest {

  @Rule public IntentsTestRule<SplashActivity> intentsTestRule =
      new IntentsTestRule<>(SplashActivity.class);

  private SplashActivity activity;

  @Before public void setUp() {
    activity = intentsTestRule.getActivity();
  }

  @Test public void testUserSignIn() {
    safeSleep(2000);
    matchToolbarTitle(activity.getString(R.string.sign_in));
    onView(withId(R.id.email)).perform(typeText("alice@gmail.com"));
    onView(withId(R.id.password)).perform(typeText("secret_password"), closeSoftKeyboard());
    safeSleep(100);
    onView(withId(R.id.sign_in_button)).perform(click());
    safeSleep(200);
    matchToolbarTitle(activity.getString(R.string.home));
    onData(anything()).inAdapterView(withId(R.id.clients_listview)).atPosition(0).perform(click());
    safeSleep(100);
    matchToolbarTitle(activity.getString(R.string.client_details));
  }
}
