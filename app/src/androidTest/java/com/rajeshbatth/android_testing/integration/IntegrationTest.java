package com.rajeshbatth.android_testing.integration;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.conf.Constants;
import com.rajeshbatth.android_testing.ui.SplashActivity;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.rajeshbatth.android_testing.TestUtils.SLEEP_INTERVAL_MEDIUM;
import static com.rajeshbatth.android_testing.TestUtils.SLEEP_INTERVAL_SMALL;
import static com.rajeshbatth.android_testing.TestUtils.matchToolbarTitle;
import static com.rajeshbatth.android_testing.TestUtils.safeSleep;
import static org.hamcrest.core.IsAnything.anything;

/**
 * Created by rajesh.j on 6/25/2015.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTest {

  @Rule
  public IntentsTestRule<SplashActivity> intentsTestRule =
      new IntentsTestRule<>(SplashActivity.class);

  private SplashActivity activity;

  @Before
  public void setUp() {
    activity = intentsTestRule.getActivity();
  }

  @Test
  public void test1UserSignIn() {
    safeSleep(Constants.SPLASH_DURATION);
    matchToolbarTitle(activity.getString(R.string.sign_in));
    onView(withId(R.id.email)).perform(typeText("alice@gmail.com"), closeSoftKeyboard());
    onView(withId(R.id.password)).perform(typeText("secret_password"), closeSoftKeyboard());
    safeSleep(SLEEP_INTERVAL_SMALL);
    onView(withId(R.id.sign_in_button)).perform(click());
    safeSleep(SLEEP_INTERVAL_MEDIUM);
    matchToolbarTitle(activity.getString(R.string.home));
    onData(anything()).inAdapterView(withId(R.id.clients_listview)).atPosition(0).perform(click());
    safeSleep(SLEEP_INTERVAL_SMALL);
    matchToolbarTitle(activity.getString(R.string.client_details));
  }

  @Test
  public void test2LaunchHome() {
    safeSleep(Constants.SPLASH_DURATION);
    matchToolbarTitle(activity.getString(R.string.home));
    onData(anything()).inAdapterView(withId(R.id.clients_listview)).atPosition(0).perform(click());
    matchToolbarTitle(activity.getString(R.string.client_details));
  }

  @Test
  public void test3Logout() {
    safeSleep(Constants.SPLASH_DURATION);
    matchToolbarTitle(activity.getString(R.string.home));
    openActionBarOverflowOrOptionsMenu(activity);
    safeSleep(SLEEP_INTERVAL_SMALL);
    onView(withText(R.string.action_logout)).perform(click());
    safeSleep(SLEEP_INTERVAL_SMALL);
    matchToolbarTitle(activity.getString(R.string.sign_in));
  }
}
