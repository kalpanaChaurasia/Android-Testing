package com.rajeshbatth.android_testing.core.auth;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.rajeshbatth.android_testing.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.rajeshbatth.android_testing.TestUtils.SLEEP_INTERVAL_SMALL;
import static com.rajeshbatth.android_testing.TestUtils.safeSleep;

/**
 * Author: Rajesh Batth
 * Date: 19-Jun-2015.
 */
@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {

  @Rule
  public ActivityTestRule<SignInActivity> activityTestRule =
      new ActivityTestRule<>(SignInActivity.class);

  /**
   * Validates the form with negative scenarios
   */
  @Test
  public void testValidations() {
    //Without email and password sign in
    onView(withId(R.id.sign_in_button)).perform(click());
    safeSleep(SLEEP_INTERVAL_SMALL);
    onView(withText(R.string.home)).check(doesNotExist());
    onView(withText(R.string.error_empty_email)).check(matches(isDisplayed()));

    //Without password sign in
    onView(withId(R.id.email)).perform(typeText("alice@gmail.com"), closeSoftKeyboard());
    safeSleep(SLEEP_INTERVAL_SMALL);
    onView(withId(R.id.sign_in_button)).perform(click());
    onView(withText(R.string.home)).check(doesNotExist());
    onView(withText(R.string.error_empty_password)).check(matches(isDisplayed()));

    //With small password sign in
    onView(withId(R.id.password)).perform(typeText("pass"), closeSoftKeyboard());
    safeSleep(SLEEP_INTERVAL_SMALL);
    onView(withId(R.id.sign_in_button)).perform(click());
    onView(withText(R.string.home)).check(doesNotExist());
    onView(withText(R.string.error_password_min_len)).check(matches(isDisplayed()));
  }

  /**
   * Checks whether home activity is launched after successful login
   */
  @Test
  public void testSignIn() {
    activityTestRule.launchActivity(new Intent());
    onView(withId(R.id.email)).perform(typeText("alice@gmail.com"));
    onView(withId(R.id.password)).perform(typeText("secret_password"), closeSoftKeyboard());
    safeSleep(SLEEP_INTERVAL_SMALL);
    onView(withId(R.id.sign_in_button)).perform(click());
    safeSleep(SLEEP_INTERVAL_SMALL);
    onView(withText(R.string.home)).check(matches(isDisplayed()));
  }
}
